package com.company

import java.lang.Math.abs
import java.util.*
import kotlin.Comparator
import kotlin.properties.Delegates
import kotlin.streams.toList


class Kotlin {
    companion object {
        fun outed(meet: Map<String, Int>, boss: String): String =
            if ((meet.values + meet[boss]!!).average() < 5.5) "Get Out Now!" else "Nice Work Champ!"

        fun getMiddle(word: String): String {
            val i = word.length / 2
            val x = word.length % 2
            if (word.length < 2) return word
            return if (x != 0) word[i].toString()
            else word.substring(i - 1, i + 1)
        }

        fun highAndLow(numbers: String): String {
//    val x = numbers.split(" ").map { it.toInt() }.sorted()
//    return "${x.last()} ${x.first()}"
            val comp = Comparator { o1: String, o2: String -> Integer.parseInt(o2) - Integer.parseInt(o1) }
            val l = numbers.split(" ").sortedWith(comp)
            return "${l.first()} ${l.last()}"
        }

        fun makeComplement(dna: String): String {
            return dna.map {
                when (it) {
                    'A' -> 'T'
                    'T' -> 'A'
                    'C' -> 'G'
                    'G' -> 'C'
                    else -> it
                }
            }.joinToString("")
        }

        //(0..n).map{ (it * it) }.sumBy { it.toString().count{ it == d.toString()[0] } }
        fun nbDig(n: Int, d: Int): Int {
            var k = 0
            val mask = Character.forDigit(d, 10)
            var counter = 0
            while (k <= n) {
                counter += (k * k).toString().count { it == mask }
                k++
            }
            return counter
        }


        private fun spinWords(sentence: String): String {
            return sentence.split(" ").joinToString(" ") { if (it.length >= 5) it.reversed() else it }
        }

        // val (even, odd) = integers.partition { it % 2 == 0 }
        //  return if (even.size == 1) even[0] else odd[0]
        fun find(arr: Array<Int>): Int {
            val p: Pair<List<Int>, List<Int>> = arr.partition { it % 2 == 0 }
            return if (p.first.size == 1) p.first[0] else p.second[0]
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val startTime = System.currentTimeMillis()
            //-------------------------
            val exampleTest1 = arrayOf(1, 1, -1, 1, 1, -44, 7, 7, 7, 7, 7, 7, 7, 7)
            println(find(exampleTest1))


            //-------------------------
            println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
        }
    }
}