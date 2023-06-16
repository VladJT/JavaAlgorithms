package jt.projects.gbandroidpro.others.delegates

import kotlin.properties.Delegates

fun main() {
    val de = StdDelegates()

    de.c = 12
    de.c = 13

    val users = mapOf("Stas" to 20, "Petr" to 32)
    val md = MapDelegateUser(users)
    println("${md.Stas} + ${md.Petr}")

}

class StdDelegates {
    // проверка на null (Exception)
    var a by Delegates.notNull<Int>()

    // делаем запрет на установку конкретных значений
    var b by Delegates.vetoable(1) { property, oldValue, newValue ->
        oldValue < newValue // условие разрешающее значения
    }

    //observable
    var c by Delegates.observable(10) { property, oldValue, newValue ->
        // что-то делаем при изменении данных
        println("$oldValue -> $newValue")
    }
}

// раскладывает map на переменные
class MapDelegateUser(val map: Map<String, Int>) {
    val Stas: Int by map // имя переменной д.б. как ключ!
    val Petr: Int by map

}
