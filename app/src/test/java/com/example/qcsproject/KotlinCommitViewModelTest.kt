package com.example.qcsproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.qcsproject.model.*
import com.example.qcsproject.repository.Repository
import com.example.qcsproject.viewModel.KotlinCommitViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class KotlinCommitViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    lateinit var viewModel:KotlinCommitViewModel
    private var observeKotlinCommits:Observer<List<KotlinCommitModel>> = mock()
    private var observerError:Observer<Boolean> = mock()
    private var observeProgressDialog:Observer<Boolean> = mock()
    private val commitAuthor = CommitAuthor("Bob","bob@hello.co.uk","1/1/1 12:12")
    private val committer = Committer("avatarturl.com")
    private val commit = Commit(commitAuthor,committer,"Initial commit")
    private val author = Author("avatarturl.com")
    private val commitsList = mutableListOf(KotlinCommitModel(commit,author,committer))


    @Before
    fun setUp(){
        viewModel = KotlinCommitViewModel(repository)

        viewModel.observeKotlinCommit().observeForever(observeKotlinCommits)
        viewModel.observeProgress().observeForever(observeProgressDialog)
        viewModel.observeError().observeForever(observerError)


    }

    @Test
    fun fetch_commit_called_showProgress(){
        `when`(repository.getKotlinCommit()).thenReturn(Single.just(commitsList))

        viewModel.getKotlinCommits()

        verify(observeProgressDialog, times(1)).onChanged(true)
    }


    @Test
    fun fetch_commit_gets_data(){


        `when`(repository.getKotlinCommit()).thenReturn(Single.just(commitsList))

        viewModel.getKotlinCommits()

        verify(observeKotlinCommits, times(1)).onChanged(commitsList)
        verify(observeProgressDialog, times(1)).onChanged(true)
    }

    @Test
    fun fetch_commit_returnsError(){

        val error = "error 1"

        `when`(repository.getKotlinCommit()).thenReturn(Single.error(RuntimeException(error)))

        viewModel.getKotlinCommits()

        verify(observeKotlinCommits, never()).onChanged(ArgumentMatchers.any())
        verify(observerError, times(1)).onChanged(true)
        verify(observeProgressDialog, times(1)).onChanged(true)
        verify(observeProgressDialog, times(1)).onChanged(false)



    }


}