package com.example.campus.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.campus.databinding.ProductsRowLayoutBinding
import com.example.campus.models.Product
import com.example.campus.models.ProductsList
import com.example.campus.util.ProductsDiffUtil

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    private var productsList = emptyList<Product>()
    class MyViewHolder(private val binding: ProductsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root){

            fun bind(product:Product){
                binding.product = product
                binding.executePendingBindings()
            }
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductsRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentProduct = productsList[position]
        holder.bind(currentProduct)
    }

    fun setData(newData:ProductsList){
        val productsDiffUtil = ProductsDiffUtil(productsList, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(productsDiffUtil)
        productsList = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}