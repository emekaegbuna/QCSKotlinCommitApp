package com.example.qcsproject.repository

import com.example.qcsproject.model.KotlinCommitModel
import io.reactivex.Flowable
import io.reactivex.Single

interface Repository {

    fun getKotlinCommit(): Single<List<KotlinCommitModel>>
}