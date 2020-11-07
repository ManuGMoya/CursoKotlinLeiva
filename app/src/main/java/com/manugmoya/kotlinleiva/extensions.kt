package com.manugmoya.kotlinleiva

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_media_item.view.*

fun Context.toast(text: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,text, length).show()
}

fun RecyclerView.ViewHolder.toast(text: String, length: Int = Toast.LENGTH_SHORT){
    itemView.context.toast(text)
}

fun ViewGroup.inflate(layoutRes: Int) : View = LayoutInflater.from(context).inflate(layoutRes, this,false)

fun ImageView.loadUrl(url: String){
    Glide.with(this).load(url).into(this)
}