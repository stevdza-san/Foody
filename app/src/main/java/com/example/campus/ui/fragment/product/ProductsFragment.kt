package com.example.campus.ui.fragment.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campus.viewmodels.MainViewModel
import com.example.campus.R
import com.example.campus.adapters.ProductAdapter
import com.example.campus.models.Product
import com.example.campus.util.NetworkResult
import com.example.campus.util.observeOnce
import com.example.campus.viewmodels.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_products.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var productViewModel: ProductViewModel
    private val mAdapter by lazy { ProductAdapter() }
    private lateinit var mView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_products, container, false)
        setupRecyclerView()
        readProductsDataBase()
        Log.d("CreateMainView", "productsFragment onCreateView")
        return mView
    }

    private fun setupRecyclerView() {
        mView.shimmer_recycler_view.adapter = mAdapter
        mView.shimmer_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun readProductsDataBase() {
         mainViewModel.readProducts.observeOnce(viewLifecycleOwner) { database ->
             if (database.isNotEmpty()) {
                 Log.d("ProductsFragment", "readDatabase called!")
                 mAdapter.setData(database[0].productsList)
                 hideShimmerEffect()
             } else {
                 requestApiData()
             }
         }
    }

    private fun requestApiData(){
        Log.d("ProductsFragment", "requestApiData called!")
        mainViewModel.getProducts(productViewModel.applyQueries())
        mainViewModel.productsResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Log.d("NetWork", "Successful")
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    // TODO question:  mainViewModel.readProducts.observe(viewLifecycleOwner) { database ->
    // lambda function in () or not
    private fun loadDataFromCache() {

        // TODO: delete later, inject data into local room database
        lifecycleScope.launch {
            mainViewModel.readProducts.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].productsList)
                }
            }
        }
    }

    private fun showShimmerEffect() {
        mView.shimmer_recycler_view.showShimmer()
    }

    private fun hideShimmerEffect() {
        mView.shimmer_recycler_view.hideShimmer()
    }

}