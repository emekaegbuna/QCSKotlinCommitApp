package com.example.qcsproject.repository

import com.example.qcsproject.model.KotlinCommitModel
import io.reactivex.Flowable

interface Repository {

    fun getKotlinCommit(): Flowable<List<KotlinCommitModel>>
}