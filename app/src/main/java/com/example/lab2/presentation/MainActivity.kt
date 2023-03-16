package com.example.lab2.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2.R

class MainActivity : AppCompatActivity(), MainFragment.OnSelectionErrorListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container_view, MainFragment())
            commit()
        }
    }

    override fun onSelectionError() {
        Toast.makeText(
            this@MainActivity,
            R.string.error_message,
            Toast.LENGTH_SHORT
        ).show()
    }
}