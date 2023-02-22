package com.example.campus.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.campus.data.Repository
import com.example.campus.data.database.ProductsEntity
import com.example.campus.models.ProductsList
import com.example.campus.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel@ViewModelInject constructor(
    private val repository: Repository,
    application: Application

):AndroidViewModel(application) {

    /** Room Database */
    val readProducts : LiveData<List<ProductsEntity>> = repository.local.readDatabase().asLiveData()

    private fun insertProducts (productsEntity: ProductsEntity) =
        viewModelScope.launch(Dispatchers.IO){
            repository.local.insertProducts(productsEntity)
    }


    /** Retrofit */
    var productsResponse: MutableLiveData<NetworkResult<ProductsList>> = MutableLiveData()

    fun getProductsUnderCategory(queries: Map<String, String>) = viewModelScope.launch {
        getProductsUnderCategorySafeCall(queries)
    }

    private suspend fun getProductsUnderCategorySafeCall(queries: Map<String, String>) {
        productsResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getProductsUnderCategory(queries)
                productsResponse.value = handleProductsRespond(response)

                // Store product data in local caching
                val product = productsResponse.value!!.data
                if (product != null) {
                    offlineCacheProducts(product)
                }
            } catch (e: Exception) {
                productsResponse.value = NetworkResult.Error("Products not found.")
            }
        } else {
            productsResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    private fun offlineCacheProducts(productsList: ProductsList) {
        val productsEntity = ProductsEntity(productsList)
        insertProducts(productsEntity)
    }

    private fun handleProductsRespond(response: Response<ProductsList>): NetworkResult<ProductsList>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Products not found.")
            }
            response.isSuccessful -> {
                val productsList = response.body()
                return NetworkResult.Success(productsList!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}
