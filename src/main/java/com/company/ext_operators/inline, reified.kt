package com.company.ext_operators

// Модификатор inline влияет и на функцию, и на лямбду, переданную ей: они обе будут встроены вместо вызова.
// Встраивание функций может увеличить количество сгенерированного кода, но если вы будете делать это в разумных пределах
// (избегая встраивания больших функций), то получите прирост производительности,
// особенно при вызове функций с параметрами разного типа внутри циклов.
// ПРАВИЛА:
// 1) стоит использовать с небольшими функциями максимум 3 строки кода + или те,что используются в циклах
// 2) параметры такой функции нельзя присваивать переменным
inline fun String.toMoney(mapper: (String) -> Money): Money {
    return mapper.invoke(this)
}

// В случае, если вы хотите, чтобы только некоторые лямбды, переданные inline-функции, были встроены,
// вам необходимо отметить модификатором noinline те функции-параметры, которые встроены не будут.
// TODO inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) { ... }

// некоторые inline-функции могут вызывать переданные им лямбды не напрямую в теле функции, а из иного контекста,
// такого как локальный объект или вложенная функция. В таких случаях, нелокальное управление потоком выполнения также запрещено в лямбдах.
// Чтобы указать это, параметр лямбды необходимо отметить модификатором crossinline.


//Reified - это ключевое слово, которое может быть использовано только в inline-функциях.
// Его цель - получение доступа к информации о типе класса.
inline fun <reified T> getType(value: T) {
    println("$value is instance of ${T::class.simpleName}")
}


class Test5 {
    var size: Int = 10

    companion object {
        val s = { a: Int, b: Int ->
            Int
            a + b
        }

        //
        fun testCycle() {
            println("start")
            listOf(1, 2, 3, 4, 5).forEach start@{
                //if (it == 3) return // выход из всей функции
                if (it == 3) return@start // выход только к метке
                println(it)
            }
            println("end")
        }


        @JvmStatic
        fun main(args: Array<String>) {
            val money = "4000".toMoney { Money(it.toInt(), Money.Currency.EUR) }
            println("money = $money")

            val c = "cool"
            println(getType(c))
            println(getType(6.7f))

            testCycle()
        }
    }
}
