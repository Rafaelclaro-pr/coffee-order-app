package com.example.pizza2.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pizza2.Domain.BannerModel
import com.example.pizza2.Domain.CategoryModel
import com.example.pizza2.Domain.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        val listData = MutableLiveData<MutableList<BannerModel>>()
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(BannerModel::class.java)
                }.toMutableList()
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "loadBanner cancelled: ${error.message}")
            }
        })
        return listData
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        val listData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(CategoryModel::class.java)
                }.toMutableList()
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "loadCategory cancelled: ${error.message}")
            }
        })
        return listData
    }

    fun loadPopular(): LiveData<MutableList<ItemsModel>> {
        val listData = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabase.getReference("Popular")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(ItemsModel::class.java)
                }.toMutableList()
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "loadPopular cancelled: ${error.message}")
            }
        })
        return listData
    }

    fun loadItemCategory(categoryId: String): LiveData<MutableList<ItemsModel>> {
        val itemsLiveData = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabase.getReference("Items")
        val query: Query = ref.orderByChild("category").equalTo(categoryId)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(ItemsModel::class.java)
                }.toMutableList()
                itemsLiveData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "loadItemCategory cancelled: ${error.message}")
            }
        })
        return itemsLiveData
    }
}
