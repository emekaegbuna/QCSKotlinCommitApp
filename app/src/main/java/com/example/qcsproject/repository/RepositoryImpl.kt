package com.example.qcsproject.repository

import com.example.qcsproject.model.KotlinCommitModel
import com.example.qcsproject.network.RetrofitInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl@Inject constructor(private val retrofitInterface: RetrofitInterface): Repository {

    override fun getKotlinCommit(): Single<List<KotlinCommitModel>> {
        return retrofitInterface.getKotlinCommit()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}