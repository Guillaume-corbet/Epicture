package com.example.epicture

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class Retrofit() {
    fun createRetrofitBuilder(): ImgurApi {
        val retrofit = Retrofit.Builder().baseUrl("https://api.imgur.com/").addConverterFactory(
            GsonConverterFactory.create()).build()

        val imgurApi = retrofit.create(ImgurApi::class.java)
        return imgurApi
    }
}