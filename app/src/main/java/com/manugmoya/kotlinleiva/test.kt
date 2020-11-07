package com.manugmoya.kotlinleiva


// CLASES
open class Person(name: String, val age: Int) {
    var name = name
    get() = ("Name: $field")
    set(value) { field = value}
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

