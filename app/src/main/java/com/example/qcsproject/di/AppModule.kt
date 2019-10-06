package com.example.qcsproject.di

import androidx.lifecycle.ViewModelProvider
import com.example.qcsproject.BuildConfig
import com.example.qcsproject.common.Constants.Companion.BASE_URL
import com.example.qcsproject.network.RetrofitInterface
import com.example.qcsproject.repository.Repository
import com.example.qcsproject.repository.RepositoryImpl
import com.example.qcsproject.viewModel.ViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: Repository): ViewModelProvider.Factory{
        return ViewModelFactory(repository)
    }
}