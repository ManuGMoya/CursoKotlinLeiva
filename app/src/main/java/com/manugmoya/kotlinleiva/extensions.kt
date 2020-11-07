package com.manugmoya.kotlinleiva

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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


// LAMBDAS CON RECEIVERS

// FUNCIÓN apply
// La función apply se devuelve a si mismo, en este caso TextView
fun TextView.apply2(body: TextView.() -> Unit): TextView {
    this.body()
    return this
}

// Esta misma función apply2 se puede hacer genérica
fun <T> T.apply3(body: T.() -> Unit): T {
    this.body()
    return this
}


// FUNCIÓN run
// La función run devuelve el objeto que devuelve la lambda
fun <T, U> T.run2(body: T.() -> U): U {
    return this.body()
}

// FUNCIÓN let
// a diferencia de let en lugar de recibir como argumento como un this, lo recibe como un it
// por lo tanto la 'T' en lugar de ponerla como 'extensión' de la lambda, se la ponemos como argumento de esta
fun <T, U> T.let2(body: (T) -> U): U {
    return body(this)
}

// FUNCIÓN with
// la función with en lugar de funcionar como una función de extension, recibe como primer argumento 'T'
fun <T, U> with2(receiver: T, body: T.() -> U): U {
    return receiver.body()
}

// FUNCiÓn also
// equivalente a let, pero se devuelve a si mismo y en lugar de ser de extencion se para el genérico por el parametro de la lambda
fun <T> T.also2(body: (T) -> Unit): T {
    body(this)
    return this
}