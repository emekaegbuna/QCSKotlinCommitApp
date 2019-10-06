package com.example.qcsproject.network

import com.example.qcsproject.common.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    fun retrofitInstance(): Retrofit {
        var loggingInterceptor = HttpLoggingInterceptor()
        var okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}