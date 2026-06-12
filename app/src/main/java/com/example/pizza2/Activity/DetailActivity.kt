package com.example.pizza2.Activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.pizza2.Domain.ItemsModel
import com.example.pizza2.Helper.ManagmentCart
import com.example.pizza2.R
import com.example.pizza2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart= ManagmentCart(this)

        bundle()
        initSizeList()
    }

    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener{

            smallBtn.setBackgroundColor(R.drawable.brown_storke_bg)
            mediumBtn.setBackgroundColor(0)
            largeBtn.setBackgroundColor(0)
            }
            mediumBtn.setOnClickListener{

                smallBtn.setBackgroundColor(0)
                mediumBtn.setBackgroundColor(R.drawable.brown_storke_bg)
                largeBtn.setBackgroundColor(0)
            }
            largeBtn.setOnClickListener{

                smallBtn.setBackgroundColor(0)
                mediumBtn.setBackgroundColor(0)
                largeBtn.setBackgroundColor(R.drawable.brown_storke_bg)
            }
        }
    }

    private fun bundle() {
        binding.apply {
            item=intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl)
                .into(binding.picMain)

            titleBtn.text=item.title
            descriptionTxt.text=item.description
            valueTxt.text="$"+item.price
            ratingTxt.text=item.rating.toString()

            addToCartBtn.setOnClickListener {
                item.numberInCart=Integer.valueOf(
                    numberInCartTxt.text.toString()

                )
                managmentCart.insertItems(item)

            }
            backBtn.setOnClickListener{ finish()}

            plusBtn.setOnClickListener{
                numberInCartTxt.text=(item.numberInCart + 1).toString()
                item.numberInCart++
            }
            minusBtn.setOnClickListener{
                if(item.numberInCart>0){
                    numberInCartTxt.text=(item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }


        }
    }
}