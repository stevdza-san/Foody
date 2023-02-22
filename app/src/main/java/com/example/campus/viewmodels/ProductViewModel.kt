package com.example.campus.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.campus.util.Constants.Companion.QUERY_CATEGORY_ID

class ProductViewModel(application: Application) : AndroidViewModel(application) {

     fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        // TODO queries for filter
        queries[QUERY_CATEGORY_ID] = "00000000-0000-0000-0000-00000000000"
        return queries
    }
}