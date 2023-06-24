package com.company.kotlin.modtypes


//Умное приведение типа
//В Kotlin есть функция умного приведения типа. Если вы проверяете тип переменной, то после
//проверки эта переменная, при положительном условии, сама приводится к проверяемому типу:
class User(var id: String, var firstName: String, var secondName: String) {
    fun setName(name: Any) {
        if (name is String) {
            firstName = name
        }
    }
}

//Проверку можно инвертировать или даже дополнить, тогда, после первой проверки на тип,
//последующие проверки будут работать уже с проверяемым типом:
class User2(var id: String = "n", var firstName: String = "s", var secondName: String = "0") {
    var age: Int = 0
    fun setData(firstName: Any, secondName: Any, age: Any) {
        if (firstName is String) {
            this.firstName = firstName
        }
        if (secondName is String && secondName.length > 0) {
            this.secondName = secondName
        }
        if (age !is Int || age < 0) {
            return
        }
        this.age = age
    }
}


fun main() {
    // умное приведение
    val u = User2()
    u.setData("2", "211", 12)
    println("${u.firstName} ${u.secondName} ${u.age}")

    //Небезопасные приведения работают через оператор as
    //Если приведение невозможно, то оператор приведения as бросает исключение. Поскольку тип String в
    //Kotlin не является nullable типом, то приведение null к строке также выбросит исключение. Если мы не
    //хотим выбрасывать исключения, то надо воспользоваться безопасным приведением через оператор
    //as?
    val i: Any = "3333"
    val j = i as String
    println(j)
}