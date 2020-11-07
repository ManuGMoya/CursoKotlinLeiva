package com.manugmoya.kotlinleiva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.manugmoya.kotlinleiva.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Es mejor hacer uso del binding evita posibles nulos
        binding.recycler.adapter = MediaAdapter(getItems()) {
            this.toast(it.title)
        }

        toast("Hello", Toast.LENGTH_LONG)

        // Ejemplo de uso de función reifield
        // startActivity<MainActivity>()

        // Ejemplo de uso de lambda con receiver
        val textView = TextView(this).apply3 {
            text = "hello"
            hint = "Goodbye"
            textSize = 22f
        }
    }


}


