package com.example.qcsproject.network

import com.example.qcsproject.model.KotlinCommitModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("commits")
    fun getKotlinCommit(): Single<List<KotlinCommitModel>>
}