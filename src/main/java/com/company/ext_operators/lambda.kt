package com.company.ext_operators

// Лямбда также известна как анонимная функция
// Лямбда-выражение возвращает значение последнего выражения в теле.
// Лямбду, у которой только один параметр, можно сократить, используя ключевое слово it:
//  val doubleLambda: (Int) -> Int = { 2 * it }
lateinit var anon_function: (Int, Int) -> Int

// Анонимный класс
val anon_class = object : Runnable {
    override fun run() {
        TODO("Not yet implemented")
    }
}


class Test4 {
    companion object {
        private fun operateOnNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
            return operation(a, b)
        }

        class Loader(val name: String, val f: (Int, Int) -> Int) {
            fun show(a: Int, b: Int) {
                val itog = f.invoke(a, b)
                println("Loader $name in ms=$itog")
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val math = { p1: Int, p2: Int ->
                Int
                p1 + p2
            }
            val l = Loader("sumclass", math)
            l.show(4, 7)

            val divideResult = operateOnNumbers(12, 3) { a: Int, b: Int -> Int
                a * b
            }
            println("a * b =  $divideResult")


        }
    }
}