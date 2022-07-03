package com.company.core

fun interface Speakable{
    fun say(s: String)
}

class C4{
    fun sayHi(speakable: Speakable){
        speakable.say("hello from C4")
    }
}

class C1:Speakable{
    override fun say(s: String) {
        println("$s + c1")
    }
}

class C2:Speakable{
    override fun say(s: String) {
        println("$s + c2")
    }
}

class TestKotlin {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val startTime = System.currentTimeMillis()
            //-------------------------

            var c4 = C4()
            var c1 = C1()
            var c2 = C2()
            c4.sayHi(object : Speakable{
                override fun say(s: String) {
                    println("$s + anon class")
                }
            })

            c4.sayHi{ s-> println("lambda + $s") }
            c4.sayHi(c1)
            c4.sayHi(c2 )


            //-------------------------
            println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
        }
    }
}