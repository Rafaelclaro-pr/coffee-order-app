package com.example.pizza2.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pizza2.Domain.ItemsModel
import com.example.pizza2.Helper.ManagmentCart
import com.example.pizza2.R
import com.example.pizza2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        bundle()
        initSizeList()
    }

    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.brown_storke_bg)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)
            }
            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(R.drawable.brown_storke_bg)
                largeBtn.setBackgroundResource(0)
            }
            largeBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.brown_storke_bg)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun bundle() {
        item = intent.getSerializableExtra("object") as ItemsModel
        item.numberInCart = 1

        binding.apply {
            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(picMain)

            titleBtn.text = item.title
            descriptionTxt.text = item.description
            valueTxt.text = "$${item.price}"
            ratingTxt.text = item.rating.toString()
            numberInCartTxt.text = item.numberInCart.toString()

            addToCartBtn.setOnClickListener {
                item.numberInCart = numberInCartTxt.text.toString().toIntOrNull() ?: 1
                managmentCart.insertItems(item)
            }

            backBtn.setOnClickListener { finish() }

            plusBtn.setOnClickListener {
                item.numberInCart++
                numberInCartTxt.text = item.numberInCart.toString()
            }

            minusBtn.setOnClickListener {
                if (item.numberInCart > 1) {
                    item.numberInCart--
                    numberInCartTxt.text = item.numberInCart.toString()
                }
            }
        }
    }
}
