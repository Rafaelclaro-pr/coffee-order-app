package com.example.pizza2.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pizza2.Adapter.ItemListCategoryAdapter
import com.example.pizza2.ViewModel.MainViewModel
import com.example.pizza2.databinding.ActivityItemsListBinding

class ItemsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemsListBinding
    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundles()
        initList()
    }

    private fun initList() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadItems(id).observe(this) {
            binding.listView.layoutManager = GridLayoutManager(this, 2)
            binding.listView.adapter = ItemListCategoryAdapter(it)
            binding.progressBar.visibility = View.GONE
        }
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun getBundles() {
        id = intent.getStringExtra("id") ?: ""
        title = intent.getStringExtra("title") ?: ""
        binding.categoryTxt.text = title
    }
}
