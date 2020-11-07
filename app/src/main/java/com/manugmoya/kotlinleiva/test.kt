package com.manugmoya.kotlinleiva

import android.view.View
import android.view.ViewGroup
import android.widget.TextView


// CLASES
open class Person(name: String, val age: Int) {
    var name = name
        get() = ("Name: $field")
        set(value) {
            field = value
        }
}

class Developer(name: String) : Person(name, 20)

/*abstract class Person2 {
    constructor(name: String, age: Int)
}

class Developer2 : Person2 {
    constructor(name: String) : super(name, 20)
    constructor(age: Int) : super("John", age)
}*/

fun test() {
    val p = Person("John", 20)
    val d = Developer("Tom")
    val name = d.name
}

// Ejemplo de when
fun test2(view: View) {
    // se puede recoger el resultado en una expresión, por defecto devolvería el objeto Unit() equivalenteb al void de Java)
    // no es necesario pasarle argumentos al when y sería con un if - else if de Java.
    val result = when (view) {
        // Se puede hace uso de llaves si tenemos varias lineas de código
        is TextView -> {
            view.text.toString()
        }
        is ViewGroup -> view.childCount.toString()
        else -> "Nothing found"
    }
}

// Ejemplos de lambdas
fun testLambda(){
    val sum: (Int, Int) -> Int = {x, y -> x + y}
    val mul: (Int, Int) -> Int = {x, y -> x * y}

    var res = doOp(2, 3, sum)
    res = doOp(2, 3, mul)

    res = doOp(2 ,3) { x, y -> x - y }


}

fun doOp(x: Int, y : Int, op: (Int, Int) -> Int) = op(x, y)

