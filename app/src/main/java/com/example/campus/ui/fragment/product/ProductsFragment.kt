package com.example.campus.ui.fragment.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campus.R
import kotlinx.android.synthetic.main.fragment_products.view.*

class ProductsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        view.shimmer_recycler_view.showShimmer()

        return view
    }

}