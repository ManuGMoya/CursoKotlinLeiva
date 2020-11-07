package com.manugmoya.kotlinleiva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.manugmoya.kotlinleiva.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Es mejor hacer uso del binding evita posibles nulos
        binding.recycler.adapter = MediaAdapter(getItems())

        toast("Hello", Toast.LENGTH_LONG)

        // Ejemplo de uso de función reifield
        // startActivity<MainActivity>()
    }



}


