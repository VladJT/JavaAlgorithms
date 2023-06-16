package com.company.kotlin.delegates

import kotlin.properties.Delegates.notNull
import kotlin.properties.Delegates.observable
import kotlin.properties.Delegates.vetoable
import kotlin.reflect.KProperty

// Делегирование класса
//Паттерн делегирование может заменить наследование, на основе этого паттерна создано много
//паттернов оберток, смысл в том, что внутри одного класса, мы держим второй класс, которому
//делегируем обязанности, то есть, вызываем его методы, при этом первый класс всю обработку
//делегирует своему второму классу. Типичный пример делегата - декоратор.
//Чтобы объявить делегат, используется ключевое слово by, после которого пишется параметр из
//конструктора. Kotlin сам генерирует методы в классе, используя переданный интерфейс как шаблон.
//Эти методы просто будут вызывать методы переданного параметра в конструкторе.
interface Base {
    var name: String
    fun method(): Any
}

class BaseImpl(override var name: String) : Base {
    override fun method(): Any {
        return name
    }
}

class Derived(base: Base) : Base by base {

}

//Делегированные свойства
//Иногда требуется делегировать свойства, например сделать ленивую инициализацию свойства, то
//есть присвоить данные, не сразу по созданию класса, а перед первым использованием.
//Само делегирование свойства происходит также при помощи указания ключевого слова by. Причем
//класс делегат должен содержать две операционных функции по установке и считыванию свойства,
//getValue и setValue:
class Example {
    var p: String by Delegate()
}

class Delegate {
    lateinit var delegateValue: String

    operator fun getValue(thisRef: Example, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating ${property.name} with value $delegateValue to me!"
    }

    operator fun setValue(thisRef: Example, property: KProperty<*>, value: String) {
        delegateValue = value
        println("$value has been assigned")
    }
}

//В Kotlin уже созданы некоторые стандартные делегаты свойств, так делегат lazy означает, что
//присваивание свойству будет сделано перед первым использованием, observable может
//запротоколировать изменения в свойстве, vetoable - проверит перед изменением свойства значение,
//и если значение не подходит, то не будет присваивать его свойству, а notNull поможет избежать
//ошибки компиляции, когда свойству будет присвоено значение не в конструкторе, избегая при этом
//lateinit.
class Delegation {
    val lazyObj: Int by lazy<Int> {
        42
    }
    var observableObj: String by observable("initial") { p, old, new ->
        println("$p.name value changed from $old to $new")
    }
    var verifiableObj: String by vetoable("initial") { p, old, new ->
        if (new.isEmpty()) false
        true
    }
    var noInit: String by notNull()
}

fun main() {
    // делегирование класса
    val base = BaseImpl("cool")
    val derived = Derived(base)
    println(derived.method())

    // делегирование свойства
    val e = Example()
    e.p = "111"
    println(e.p)

    // некоторые стандартные делегаты свойств
    val d = Delegation()
    d.observableObj = "@@@"
}
