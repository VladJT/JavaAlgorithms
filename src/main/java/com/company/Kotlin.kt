package com.company


class Kotlin {
    companion object {


        fun outed(meet: Map<String, Int>, boss: String): String =
            if ((meet.values + meet[boss]!!).average() < 5.5) "Get Out Now!" else "Nice Work Champ!"


        fun getMiddle(word: String): String {
            val i = word.length / 2
            val x =  word.length % 2
            if (word.length < 2) return word
            return if(x!=0) word[i].toString()
            else word.substring(i-1,i+1)
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val startTime = System.currentTimeMillis()
            //-------------------------
            println(getMiddle("middle"))
            println(getMiddle("testing"))
            println(getMiddle("A"))


            //-------------------------
            println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
        }
    }
}