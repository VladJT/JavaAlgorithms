package com.company

import java.util.*
import java.util.regex.Pattern


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


        //fun persistence(num: Int) = if (num < 10) 0 else 1 + persistence(num.toString().map { it - '0' }.reduce(Int::times))
        fun persistence(num: Int): Int {
            var counter = 0
            if (num >= 10) {
                counter = 1 + persistence(num.toString().toCharArray().map { (it.code - 48) }.reduce(Int::times))
            }
            return counter
        }

        // fun findEvenIndex(arr: IntArray) = arr.indices.indexOfFirst { arr.take(it).sum() == arr.drop(it + 1).sum() }
        fun findEvenIndex(arr: IntArray): Int {
            for (i in arr.indices) {
                var sumLeft = 0
                if (i > 0) sumLeft = arr.slice(0 until i).sum()
                var sumRight = 0
                if (i < arr.lastIndex) sumRight = arr.slice(i + 1..arr.lastIndex).sum()
                if (sumLeft == sumRight) return i
            }
            return -1
        }

        fun dirReduc(arr: Array<String>): Array<String> {
//         val s = Stack<String>()
//        arr.forEach {
//            when (it) {
//                "NORTH" -> if (s.isNotEmpty() && s.peek() == "SOUTH") s.pop() else s.push(it)
//                "SOUTH" -> if (s.isNotEmpty() && s.peek() == "NORTH") s.pop() else s.push(it)
//                "EAST" -> if (s.isNotEmpty() && s.peek() == "WEST") s.pop() else s.push(it)
//                "WEST" -> if (s.isNotEmpty() && s.peek() == "EAST") s.pop() else s.push(it)
//            }
//        }
//        return s.toList().toTypedArray()

            var i = 0
            val a = mutableListOf<String>()
            a.addAll(arr)
            while (i < a.lastIndex && a.size > 1) {
                if ((a[i] == "EAST" && a[i + 1] == "WEST") || (a[i] == "WEST" && a[i + 1] == "EAST") ||
                    (a[i] == "NORTH" && a[i + 1] == "SOUTH") || (a[i] == "SOUTH" && a[i + 1] == "NORTH")
                ) {
                    a.removeAt(i)
                    a.removeAt(i)
                    if (i > 0) i--
                    continue
                } else i++
            }
            return a.toTypedArray()
        }


        fun longestConsec(strarr: Array<String>, k: Int): String {
            val n = strarr.size
            return if (n == 0 || k > n || k <= 0) ""
            else strarr.asSequence()
                .windowed(k)
                .map { it.joinToString("") }
                .maxBy { it.length } ?: ""
        }

        // RGB To Hex Conversion
        fun rgb(r: Int, g: Int, b: Int): String {
            return "${hex(r)}${hex(g)}${hex(b)}"
        }

        fun hex(i: Int): String {
            var x = i
            if (i < 0) x = 0
            if (i > 255) x = 255
            val s = Integer.toHexString(x).uppercase(Locale.getDefault())
            return if (s.length < 2) "0$s" else s
        }

        // String incrementer
        fun incrementString(str: String): String {
            val pattern = Pattern.compile("[0-9]+$")
            val matcher = pattern.matcher(str)
            return if (matcher.find()) {
                val r = str.substring(matcher.start(), matcher.end())
                val x = (r.toInt() + 1).toString()
                var n = r.length - x.length
                if (n < 0) n = 0
                str.substring(0, matcher.start()).plus("0".repeat(n).plus(x))
            } else {
                when (val x = str.toIntOrNull()) {
                    null -> str.plus("1")
                    else -> (x + 1).toString()
                }
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val startTime = System.currentTimeMillis()
            //-------------------------

            println(incrementString("foobar001"))//foobar002
            println(incrementString("foobar999"))//foobar01000
            println(incrementString("009"))//foo1
            println(incrementString(""))//1


            //-------------------------
            println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
        }
    }
}