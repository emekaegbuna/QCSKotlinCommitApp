package com.example.qcsproject.di

import com.example.qcsproject.view.KotlinCommitFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(kotlinCommitFragment: KotlinCommitFragment)

}