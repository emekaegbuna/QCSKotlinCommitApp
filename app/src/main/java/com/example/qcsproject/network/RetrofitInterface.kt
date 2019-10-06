package com.example.qcsproject.network

import com.example.qcsproject.model.KotlinCommitModel
import io.reactivex.Flowable
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("commits")
    fun getKotlinCommit(): Flowable<List<KotlinCommitModel>>
}