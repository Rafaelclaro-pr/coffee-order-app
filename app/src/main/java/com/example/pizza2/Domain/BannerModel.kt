package com.example.pizza2.Domain

import android.util.Log

data class BannerModel(val url: String = "") {
    init {
        Log.d("BannerModel", "Created with URL: $url") // Logs when the object is created
    }

    // Override toString() to print URL when the object is logged
    override fun toString(): String {
        Log.d("BannerModel", "URL: $url") // Logs when toString() is called
        return "BannerModel(url='$url')"
    }
}