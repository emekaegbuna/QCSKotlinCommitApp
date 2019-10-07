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

    private var compositeDisposable = CompositeDisposable()
    private var liveKotlinCommit: MutableLiveData<List<KotlinCommitModel>> = MutableLiveData()
    private var liveError: MutableLiveData<Boolean> = MutableLiveData()
    private var liveProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun getKotlinCommits(){



        compositeDisposable.add(
        repository
            .getKotlinCommit()
            .doOnSubscribe {liveProgress.value = true}
            .subscribe({commits -> setKotlinCommit(commits)
            liveProgress.value = false}, {error -> handleError(error.message!!)
            liveProgress.value = false}))
    }

    private fun setKotlinCommit(kotlinCommitModel: List<KotlinCommitModel>){

        liveProgress.value = false
        liveKotlinCommit.value = kotlinCommitModel
    }

    fun observeKotlinCommit(): MutableLiveData<List<KotlinCommitModel>>{
        return liveKotlinCommit
    }

    private fun handleError(error: String){
        liveError.value = true
    }

    fun observeError():MutableLiveData<Boolean>{

        return liveError
    }

    fun observeProgress(): MutableLiveData<Boolean>{
        return liveProgress
    }

}
