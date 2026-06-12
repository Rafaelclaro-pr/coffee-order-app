package com.example.pizza2.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizza2.Adapter.CartAdapter
import com.example.pizza2.Helper.ChangeNumberItemsListener
import com.example.pizza2.Helper.ManagmentCart
import com.example.pizza2.databinding.ActivityCartBinding
import kotlin.math.round

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartList()
    }

    private fun initCartList() {
        binding.listView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.listView.adapter = CartAdapter(
            managmentCart.getListCart(),
            this,
            object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }
            }
        )
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15.0
        val subtotal = round(managmentCart.getTotalFee() * 100) / 100
        val tax = round(subtotal * percentTax * 100) / 100
        val total = round((subtotal + tax + delivery) * 100) / 100
        binding.apply {
            totalFeeTxt.text = "$$subtotal"
            totalTaxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }
}
