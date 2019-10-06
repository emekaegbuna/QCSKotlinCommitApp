package com.example.qcsproject.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qcsproject.model.KotlinCommitModel
import com.example.qcsproject.network.RetrofitInterface
import com.example.qcsproject.repository.Repository
import com.example.qcsproject.repository.RepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class KotlinCommitViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    //lateinit var repository: RepositoryImpl
    lateinit var compositeDisposable: CompositeDisposable
    var liveKotlinCommit: MutableLiveData<List<KotlinCommitModel>> = MutableLiveData()
    var liveError: MutableLiveData<Boolean> = MutableLiveData()
    var liveProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun getKotlinCommits(){

        liveProgress.value = true

        //repository = RepositoryImpl(RetrofitInterface)
        compositeDisposable = CompositeDisposable()

        compositeDisposable.add(
        repository
            .getKotlinCommit()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({t -> setKotlinCommit(t) }, {error -> handleError(error.message!!)}))
    }

    fun setKotlinCommit(kotlinCommitModel: List<KotlinCommitModel>){

        liveProgress.value = false
        liveKotlinCommit.value = kotlinCommitModel
    }

    fun observeKkotlinCommit(): MutableLiveData<List<KotlinCommitModel>>{
        return liveKotlinCommit
    }

    fun handleError(error: String){
        Log.d("TAG_ViewModel", error)
        liveError.value = true
    }

    fun observeError():MutableLiveData<Boolean>{

        return liveError
    }

    fun observeProgress(): MutableLiveData<Boolean>{
        return liveProgress
    }

}
