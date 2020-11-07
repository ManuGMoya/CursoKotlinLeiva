package com.manugmoya.kotlinleiva

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_media_item.view.*

class MediaAdapter(
    private val items: List<MediaItem>
) : RecyclerView.Adapter<MediaAdapter.ItemViewHolder>() {

    // Numero de items
    override fun getItemCount(): Int = items.size

    // Es llamado para crear una nueva celda
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.inflate(R.layout.view_media_item))

    }

    // Asignamos los valores correspondientes
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }



    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: MediaItem) = with(itemView) {
            mediaTitle.text = item.title
            mediaThumb.loadUrl(item.url)

            setOnClickListener {
                toast(item.title)
            }
        }
    }


}