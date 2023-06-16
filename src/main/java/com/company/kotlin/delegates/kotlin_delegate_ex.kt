package jt.projects.gbandroidpro.others.delegates

import kotlin.reflect.KProperty


interface Nameable {
    var name: String
}

class JackName : Nameable {
    override var name = "Jack"
}

class LongDistanceRunner : Runnable {
    override fun run() {
        println("long")
    }

}

class Person(name: Nameable, runner: Runnable) : Nameable by name, Runnable by runner

fun main() {
//    val p = Person(JackName(), LongDistanceRunner())
//    println(p.name)
//    p.run()
//
//    val ex = DelegatedPropEx()
//    ex.name = "John"
//    ex.surname = "Taler"
//    println(ex.surname)

    val l = listOf<Int>(1, 2, 3, 4)
    val sum by MyLazy { l }
    println("!! $sum")
}

class MyLazy(i: () -> List<Int>) {
    private var value = 0

    init {
        i.invoke().forEach {
            value += it
        }
    }

    operator fun getValue(thisRef: Nothing?, property: KProperty<*>): Any {
        return "[$thisRef] ${property.name}, result SUM = $value"
    }

}

/**
 * Иногда геттеры и сеттеры содержат одинаковый код.
var str: String
get() = this.toString()
set(value) {
println(value)
field = value
}
Чтобы уйти от рутинного копирования или просто
инкапсулировать этот код в отдельном месте, можно использовать Delegated Properties
 */
class DelegatedPropEx {
    var name by Delegate()
    var surname by Delegate()
}

class Delegate {
    operator fun getValue(delegatedPropEx: DelegatedPropEx, property: KProperty<*>): Any {
        return "$delegatedPropEx, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(delegatedPropEx: DelegatedPropEx, property: KProperty<*>, any: Any) {
        println("$any has been assigned to '${property.name}' in $delegatedPropEx.")
    }
}