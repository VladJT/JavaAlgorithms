package com.company.ext_operators

import kotlin.random.Random


class Test3 {
    companion object {
        lateinit var a: String
        var b: String? = null

        // инициализируется только 1 раз(!!) в момент 1го обращения
        val c by lazy {
            (1..10).random()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            a= "Hello"
            b="World"
            println("a=$a, b=$b, c=$c")
            println("a=$a, b=$b, c=$c")

        }
    }
}