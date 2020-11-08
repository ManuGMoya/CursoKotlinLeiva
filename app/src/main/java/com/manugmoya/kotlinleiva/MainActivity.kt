package com.manugmoya.kotlinleiva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.manugmoya.kotlinleiva.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private val adapter = MediaAdapter { this.toast(it.title) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Es mejor hacer uso del binding evita posibles nulos
        // en esta linea es donde se va a ejecutar el código del adapter by lazy
        binding.recycler.adapter = adapter

        updateItems()

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

    private fun updateItems(filter: Int = R.id.filter_all) {
        // Uso de corrutinas
        GlobalScope.launch(Dispatchers.Main) {
            progress.visibility = View.VISIBLE
            val items = withContext(Dispatchers.IO) {
                getFilteredItems(filter)
            }
            adapter.items = items
            progress.visibility = View.GONE
        }
    }

    private fun getFilteredItems(filter: Int): List<MediaItem> {
        return MediaProvider.getItems().let { media ->
            when (filter) {
                R.id.filter_all -> {
                    media
                }
                R.id.filter_videos -> {
                    media.filter { it.type == MediaItem.Type.VIDEO }
                }
                R.id.filter_photos -> {
                    media.filter { it.type == MediaItem.Type.PHOTO }
                }
                else -> emptyList()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        updateItems(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}


