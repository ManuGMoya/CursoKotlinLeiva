package com.manugmoya.kotlinleiva

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.manugmoya.kotlinleiva.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Para generar nuestro scope extendemos de CoroutineScope
class MainActivity : AppCompatActivity()
    //, CoroutineScope
{

/*    // Necesitamos el dispacher por defecto y un job, que es el trabajo al que se asocian las corrutinas
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    // Lo hacemos lateinit para recrearlo cada vez que se recree la activity
    private lateinit var job: Job*/

    private val adapter = MediaAdapter {
        startActivity<DetailActivity>(DetailActivity.EXTRA_ID to it.id)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // se hace uso de SupervisorJob() para en caso de que una corrutina se cancele, las demás no lo hagan.
        // job = SupervisorJob()

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
        // Uso de corrutinas - El uso de GlobalScope no está recomendado porque sobrevive durante todo
        // el ciclo de la aplicación. Por lo que nos creamos nuestro propio scope
        lifecycleScope.launch {
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

    override fun onDestroy() {
        // job.cancel()
        super.onDestroy()
    }
}


