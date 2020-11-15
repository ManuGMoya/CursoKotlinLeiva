package com.manugmoya.kotlinleiva

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manugmoya.kotlinleiva.databinding.ViewMediaItemBinding
import kotlin.properties.Delegates

typealias Listener = (MediaItem) -> Unit

class MediaAdapter(
    items: List<MediaItem> = emptyList(), private val listener: Listener
) : RecyclerView.Adapter<MediaAdapter.ItemViewHolder>() {

    // hay que hacerla publica y var para poder modificarla desde fuera de esta clase.
    var items: List<MediaItem> by Delegates.observable(items) { _, _, _ ->
        notifyDataSetChanged()
    }

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
        holder.itemView.setOnClickListener { listener.invoke(item) }
    }


    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ViewMediaItemBinding.bind(view)

        fun bind(item: MediaItem) = with(binding) {
            mediaTitle.text = item.title
            mediaThumb.loadUrl(item.url)
            mediaVideoIndicator.visibility = when (item.type) {
                MediaItem.Type.PHOTO -> View.INVISIBLE
                MediaItem.Type.VIDEO -> View.VISIBLE
            }
/*            root.setOnClickListener {
                toast(item.title)
            }*/
        }
    }

}
