package com.manugmoya.kotlinleiva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.manugmoya.kotlinleiva.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "DetailActivity:id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, 0)

        getItem(id)

    }


    private fun getItem(id: Int) {
        lifecycleScope.launch{
            val item = withContext(Dispatchers.IO){
                MediaProvider.getItems().firstOrNull{
                    it.id == id
                }
            }

            item?.let {
                supportActionBar?.title = item.title
                detail_thumb.loadUrl(item.url)
                detail_video_indicator.visibility = if(item.type == MediaItem.Type.VIDEO){
                    View.VISIBLE
                }else{
                    View.GONE
                }
            }
        }
    }
}