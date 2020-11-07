package com.manugmoya.kotlinleiva

import android.app.Activity
import android.content.Context
import android.content.Intent
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

// inline es para en lugar de llamar a la función, sustituir la llamada a la función
// por la llamada al código que está dentro de la función de modo que con el reifield
// no se pierde la información del tipo genérico
// Por lo tanto para hacer uso de reifield() genéricos es necesarion que vaya acompañado de inline.
inline fun  <reified T : Activity> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}