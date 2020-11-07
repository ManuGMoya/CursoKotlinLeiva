package com.manugmoya.kotlinleiva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.manugmoya.kotlinleiva.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // Ejemplo de uso de lazy, el código no se ejecutará hasta que la propiedad no sea llamada
    private val adapter by lazy {
        MediaAdapter(getItems()) {
            this.toast(it.title)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Es mejor hacer uso del binding evita posibles nulos
        // en esta linea es donde se va a ejecutar el código del adapter by lazy
        binding.recycler.adapter = adapter

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        adapter.items = getItems().let {media ->
            when(item.itemId) {
                R.id.filter_all -> {
                    media
                }
                R.id.filter_videos -> {
                    media.filter { it.type == MediaItem.Type.VIDEO }
                }
                R.id.filter_photos -> {
                    media.filter { it.type == MediaItem.Type.VIDEO }
                }
                else -> emptyList()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}


