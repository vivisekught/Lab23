package com.example.lab2.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2.R

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container_view, MainFragment())
            commit()
        }
    }
}