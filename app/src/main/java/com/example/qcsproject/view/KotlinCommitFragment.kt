package com.example.qcsproject.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qcsproject.viewModel.KotlinCommitViewModel
import com.example.qcsproject.R
import com.example.qcsproject.di.AppModule
import com.example.qcsproject.di.DaggerAppComponent
import com.example.qcsproject.di.RepositoryModule
import com.example.qcsproject.model.KotlinCommitModel
import com.example.qcsproject.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.kotlin_commit_fragment.*
import javax.inject.Inject


class KotlinCommitFragment : Fragment() {



    companion object {
        fun newInstance() = KotlinCommitFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel : KotlinCommitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.kotlin_commit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        DaggerAppComponent.builder()
            .appModule(AppModule())
            .repositoryModule(RepositoryModule())
            .build()
            .inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(KotlinCommitViewModel::class.java)

        viewModel.getKotlinCommits()

        viewModel.observeKkotlinCommit()
            .observe(this, object : Observer<List<KotlinCommitModel>>{
                override fun onChanged(t: List<KotlinCommitModel>) {
                    var adapter = KotlinCommitAdapter(t)
                    var layoutManager = LinearLayoutManager(activity!!.applicationContext)
                    rv_kotlin_commit.layoutManager = layoutManager
                    rv_kotlin_commit.adapter = adapter
                }

            })
    }

}
