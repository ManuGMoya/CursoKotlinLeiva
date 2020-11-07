package com.manugmoya.kotlinleiva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = MediaAdapter(getItems())

        toast("Hello", Toast.LENGTH_LONG)

        // Ejemplo de uso de función reifield
        // startActivity<MainActivity>()
    }



}


