package com.example.qcsproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.qcsproject.repository.Repository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KotlinCommitViewModel(repository) as T
    }
}