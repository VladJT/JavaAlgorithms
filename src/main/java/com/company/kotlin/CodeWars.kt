package com.company.kotlin

import java.time.LocalDate
import java.util.*
import java.util.regex.Pattern
import kotlin.math.abs


class CodeWars {
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
                if ((a[i] == "EAST" && a[i + 1] == "WEST") || (a[i] == "WEST" && a[i + 1] == "EAST") || (a[i] == "NORTH" && a[i + 1] == "SOUTH") || (a[i] == "SOUTH" && a[i + 1] == "NORTH")) {
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
            else strarr.asSequence().windowed(k).map { it.joinToString("") }.maxBy { it.length } ?: ""
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

        // Find the missing letter
        // fun findMissingLetter(array: CharArray) = (array.first()..array.last()).first { it !in array }
        fun findMissingLetter(array: CharArray): Char {
            var i = 1
            while (i < array.size) {
                if (array[i] - array[i - 1] != 1) return (array[i] - 1)
                i++
            }
            return ' '
        }

        // fun top3(s: String): List<String> =
        //    s.toLowerCase()
        //        .split("[^a-z']+".toRegex())
        //        .filter { it.contains("[a-z]".toRegex()) }
        //        .groupingBy { it }
        //        .eachCount()
        //        .entries
        //        .sortedByDescending { it.value }
        //        .take(3)
        //        .map { it.key }
        fun top3(s: String): List<String> {
            //    val list = s.replace("[/\\\\#.,!]".toRegex(), " ")
            val list = s
                .replace("_", " ")
                .replace("'", "_")
                .replace("[\\W]".toRegex(), " ")
                .replace("_", "'")
                .lowercase(Locale.getDefault()).split(" ").filter { it.isNotBlank() && !it.matches("^'+$".toRegex()) }

            val map = mutableMapOf<String, Int>()

            list.forEach {
                if (map.containsKey(it)) {
                    map[it] = map[it]!! + 1
                } else {
                    map[it] = 1
                }
            }

            val result =
                map.entries.sortedByDescending { it.value }
                    .take(3)
                    .map {
                        it.key
                    }.toList()

            return result
        }

        fun date_nb_days(a0: Double, a: Double, p: Double): String {
            var startDate = LocalDate.of(2016, 1, 1)
            var startMoney = a0
            var days: Long = 0
            while (startMoney <= a) {
                startMoney += startMoney * p / 100 / 360
                days++
            }
            return startDate.plusDays(days).toString()
        }


        //   if(zipcode.isEmpty()){
        //      return ":/"
        //    }
        //    var matches = r.split(",").filter{it.endsWith(zipcode)}.map { it.replace(" $zipcode", "") }
        //
        //    val numbers = matches.map { it.substringBefore(" ") }.joinToString(",")
        //    val streets = matches.map { it.substringAfter(" ") }.joinToString(",")
        //
        //    return "$zipcode:$streets/$numbers"
        val adresses =
            "123 Main Street St. Louisville OH 43071,432 Main Long Road St. Louisville OH 43071,786 High Street Pollocksville NY 56432"

        fun travel(r: String, zipcode: String): String {
            var streets = ""
            var nums = ""

            r.split(",").map { address ->
                val formattedAddress = address
                    .replace("(^[\\d]+)\\s".toRegex(), "$1|")
                    .replace("\\s(..\\s[\\d]+)".toRegex(), "|$1")
                    .split("|")

                val _num = formattedAddress[0]
                val _street = formattedAddress[1]
                val _zip = formattedAddress[2]

                if (_zip == zipcode) {
                    streets += _street + ","
                    nums += _num + ","
                }
            }

            streets = streets.replace(",$".toRegex(), "")
            nums = nums.replace(",$".toRegex(), "")

            return "$zipcode:$streets/$nums"
        }


        // multiplication table
        fun multiplicationTable(size: Int): Array<IntArray> =
            Array(size) { i -> IntArray(size) { j -> (i + 1) * (j + 1) } }

        fun balanceStatements(lst: String): String {
            if (lst.isBlank()) return "Buy: 0 Sell: 0"

            var bad = mutableListOf<String>()
            var b = 0.0
            var s = 0.0

            for (order in lst.split(", ")) {
                val match = "^([^\\s]+)\\s(\\d+)\\s(\\d+\\.\\d+)\\s([BS])$".toRegex().find(order)

                if (match == null) bad.add(order + " ;")
                else {
                    var (m, quote, quantity, price, t) = match.groupValues
                    val p = quantity.toInt() * price.toDouble()
                    if (t == "B") b += p else if (t == "S") s += p
                }
            }
            val bf = if (bad.isEmpty()) "" else "; Badly formed ${bad.size}: ${bad.joinToString("")}"
            return String.format("Buy: %.0f Sell: %.0f%s", b, s, bf)
        }

        @JvmStatic


        fun main(args: Array<String>) {
            //val startTime = System.currentTimeMillis()
            //-------------------------
            // val expected = arrayOf(intArrayOf(0,27), intArrayOf(1,13), intArrayOf(2,14), intArrayOf(6,7))

            val input = arrayOf(
                intArrayOf(21, 9, 10, 4, 5, 4, 6, 2, 2, 1, 4, 1, 3, 2, 4),
                intArrayOf(19, 9, 12, 4, 5, 4, 6, 2, 2, 1, 4, 1, 3, 2, 4),
                intArrayOf(19, 9, 10, 5, 5, 4, 6, 2, 2, 1, 4, 1, 3, 2, 4),
                intArrayOf(19, 9, 10, 4, 5, 4, 6, 3, 2, 1, 4, 1, 3, 2, 4),
                intArrayOf(28, 13, 14, 6, 7, 5, 9),
                intArrayOf(27, 14, 14, 6, 7, 5, 9),
                intArrayOf(27, 13, 15, 6, 7, 5, 9),
                intArrayOf(29, 13, 16, 5, 8, 9, 1)
            )

            for (i in input) {
                println(findIncorrectNumber(i).toList())
            }

            //-------------------------
            // println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
        }


        val result = intArrayOf(0, 0)

        fun findIncorrectNumber(tree: IntArray): IntArray {
            find(0, 0, tree, true)
            return result
        }

        fun find(curIndex: Int, level: Int, tree: IntArray, parentBad: Boolean) {
            var child1 = curIndex * 2 +1
            var child2 = curIndex * 2 +2

            if (child2 > tree.lastIndex) return

            if (tree[curIndex] != tree[child1] + tree[child2]) {
                if (parentBad) {
                    result[0] = curIndex
                    result[1] = tree[child1] + tree[child2]
                } else {
                    result[0] = child2
                    result[1] = tree[curIndex] - tree[child1]
                }
            }

            val parBad = tree[curIndex] != tree[child1] + tree[child2]
            find(child1, level + 1, tree, parBad)
            find(child2, level + 1, tree, parBad)
        }


        fun doSqrSortedList(inputArray: IntArray): IntArray {

            var result = mutableListOf<Int>()
            var left = 0
            var right = inputArray.size - 1

            // -3, 2, 4
            // 0
            // 2
            //
            while (left <= right) {
                if (inputArray[left].sqr() >= inputArray[right].sqr()) {
                    result.add(inputArray[left].sqr())
                    left++
                } else {
                    result.add(inputArray[right].sqr())
                    right--
                }
            }
            return result.reversed().toIntArray()
        }

        private fun Int.sqr(): Int = this * this

        fun elevator(left: Int, right: Int, call: Int): String =
            if (abs(call - left) < abs(call - right)) "left" else "right"


    }
}