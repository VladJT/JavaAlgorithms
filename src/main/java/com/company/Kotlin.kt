package com.company

class Kotlin {
    companion object {

        class Lesson3{
            fun recieve(s:String){
                println("$s получен")
            }
        }

        class Lesson4{
            lateinit var lesson3: Lesson3

            fun sayHitoLesson3(s: String){
                lesson3.recieve(s)
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val startTime = System.currentTimeMillis()
            //-------------------------
            var l3 = Lesson3()
            var l4 = Lesson4()
            l4.lesson3 = l3
            l4.sayHitoLesson3("hello")



            //-------------------------
            println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
        }

    }

}