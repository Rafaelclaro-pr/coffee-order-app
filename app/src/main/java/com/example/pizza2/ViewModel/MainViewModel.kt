package com.example.pizza2.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pizza2.Domain.BannerModel
import com.example.pizza2.Domain.CategoryModel
import com.example.pizza2.Domain.ItemsModel
import com.example.pizza2.Repository.MainRepository

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>> = repository.loadBanner()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> = repository.loadCategory()

    fun loadPopular(): LiveData<MutableList<ItemsModel>> = repository.loadPopular()

    fun loadItems(categoryId: String): LiveData<MutableList<ItemsModel>> =
        repository.loadItemCategory(categoryId)
}
