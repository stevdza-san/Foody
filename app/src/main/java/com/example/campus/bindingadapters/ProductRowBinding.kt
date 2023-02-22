package com.example.campus.bindingadapters

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.campus.R

class ProductRowBinding {

    companion object {

        // TODO load image

        @BindingAdapter("applySellingColor")
        @JvmStatic
        fun applyVeganColor(view: View, isSold: Int) {
            if(isSold == 0){
                when(view){
                    is ImageView -> {
                        view.setColorFilter(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                    }
                }
            }
        }
    }


}