package kr.co.bepo.arcticfoxprogaramming.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.co.bepo.arcticfoxprogaramming.Product
import kr.co.bepo.arcticfoxprogaramming.ProductRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository = ProductRepository(application)
    private val allProducts: LiveData<List<Product>>?
    private val searchResults: MutableLiveData<List<Product>>

    init {
        allProducts = repository.allProducts
        searchResults = repository.searchResults
    }

    fun getAllProducts(): LiveData<List<Product>>? {
        return allProducts
    }

    fun insertProduct(product: Product) {
        repository.insertProduct(product)
    }

    fun findProduct(name: String) {
        repository.findProduct(name)
    }

    fun deleteProduct(name: String) {
        repository.deleteProduct(name)
    }

    fun getSearchResults(): MutableLiveData<List<Product>> {
        return searchResults
    }
}