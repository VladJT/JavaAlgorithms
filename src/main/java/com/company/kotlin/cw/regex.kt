package com.company.kotlin.cw


fun main(args: Array<String>) {
    val startTime = System.currentTimeMillis()
    //-------------------------

    //        runTest("aaaabb", Pair('a',4))
    //        runTest("bbbaaabaaaa", Pair('a',4))
    //        runTest("cbdeuuu900", Pair('u',3))
    //        runTest("abbbbb", Pair('b',5))
    //        runTest("aabb", Pair('a',2))
    //        runTest("", Pair(null,0))
    //        runTest("ba", Pair('b',1))

    println(longestRepetition("bbbaaabaaaa"))


    //-------------------------
    println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
}


fun encryptThis(text: String): String {

    return text
        .replace("(\\w)(\\w)?(\\w+)?(\\w)([\\s]|$)".toRegex()) { result ->
            val firstCharCode = result.groupValues[1].get(0).code.toString()
            firstCharCode + result.groupValues[4] + result.groupValues[3] + result.groupValues[2] + result.groupValues[5]
        }
        // Замена слов длиной == 1
        .replace("\\b(\\w)\\b".toRegex()) { oneCharWord ->
            oneCharWord.groupValues[1][0].code.toString()
        }


}

fun longestRepetition(s: String): Pair<Char?, Int> {
    var result :Pair<Char?, Int>  = Pair(null, 0)
    var len = 0
    var currChar: Char? = null
    for (c in s) {
        if (currChar == null || currChar == c) {
            currChar = c
            len++
        } else {
            if (result.second < len) {
                result = Pair(currChar, len)
            }
            currChar = c
            len = 1
        }
    }
    if (result.second < len) {
        result = Pair(currChar, len)
    }
    return result
}
