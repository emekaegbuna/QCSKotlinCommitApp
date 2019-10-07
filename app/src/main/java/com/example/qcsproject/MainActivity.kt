package com.example.qcsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qcsproject.view.KotlinCommitFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment()
    }

    private fun addFragment(){
        val fragmentManger = supportFragmentManager
        val fragmentTransaction = fragmentManger.beginTransaction()

        fragmentTransaction.add(
            R.id.fl_container,
            KotlinCommitFragment()
        )
            //.addToBackStack(null)
            .commit()
    }
}
