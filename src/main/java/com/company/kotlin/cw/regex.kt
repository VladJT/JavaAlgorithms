package com.company.kotlin.cw


fun main(args: Array<String>) {
    val startTime = System.currentTimeMillis()
    //-------------------------

    //87yh 99na 119e 110to 97ll 98e 108eki 116tah 119esi 111dl 98dri
    //87yh 99na 119e 110to 97ll 98e 108eki 116tah 119esi 111dl 98dri
    println(encryptThis("Why can we not all be like that wise old bird"))
    //-------------------------
    println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
}


fun encryptThis(text:String): String{

    return text
    .replace("(\\w)(\\w)?(\\w+)?(\\w)([\\s]|$)".toRegex()) { result ->
        result.groupValues[1].get(0).code.toString() + result.groupValues[4]  +  result.groupValues[3] + result.groupValues[2] + result.groupValues[5]
    }

}