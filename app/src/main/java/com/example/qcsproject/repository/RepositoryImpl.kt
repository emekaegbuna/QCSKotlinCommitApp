package com.example.qcsproject.repository

import com.example.qcsproject.model.KotlinCommitModel
import com.example.qcsproject.network.RetrofitInstance
import com.example.qcsproject.network.RetrofitInterface
import io.reactivex.Flowable
import javax.inject.Inject

class RepositoryImpl@Inject constructor(private val retrofitInterface: RetrofitInterface): Repository {

    override fun getKotlinCommit(): Flowable<List<KotlinCommitModel>> {
        return retrofitInterface.getKotlinCommit()
    }
}