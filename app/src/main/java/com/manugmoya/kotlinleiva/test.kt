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
fun testLambda() {
    val sum: (Int, Int) -> Int = { x, y -> x + y }
    val mul: (Int, Int) -> Int = { x, y -> x * y }

    var res = doOp(2, 3, sum)
    res = doOp(2, 3, mul)

    res = doOp(2, 3) { x, y -> x - y }


}

fun doOp(x: Int, y: Int, op: (Int, Int) -> Int) = op(x, y)

// Ejemplos de colecciones
fun testCollections() {

    val listOfInt: List<Int> = listOf(2, 4, 5)

    // Operaciones con listas
    val result = listOfInt.filter { it % 2 == 0 }
        .map { it.toString() }
        .sorted()

    val emptyList = emptyList<Int>()
    val newList = emptyList + 3
    val newList2 = emptyList + newList

    val emptyMutableList : MutableList<Int> = mutableListOf()

    // set No permite elementod repetidos
    val set = setOf<Int>(3,4,5,6,3)

    val map = mapOf("a" to 1, Pair("b", 2)) // Otra manera de usar pares es con la función infix 'to'

    // FUNCIONES INFIX

    for(i in 0.until(10)){ // until es en realidad una función infix

    }

    0 until2 10 // utilizando la palabra reservada 'infix' podremos llamar a la función de esta manera

}

infix fun Int.until2(x: Int) {

}

// OBJECTS - Son clases que tienen una única instancia, como un singleton. No pueden recibir parametros.
object MyObject{
    var x = 20
    val y = "Hello"
}

fun testObject(){
    MyObject.x = 300
}


// Propiedades de extensión
fun testPropertyExtension(viewGroup: ViewGroup) {
    viewGroup.size
    viewGroup[0]
}

val ViewGroup.size: Int
    get() = childCount

// Sobrecarga de operadores - hay que añadir la palabra reservada operator
operator fun ViewGroup.get(index: Int): View = getChildAt(index)
