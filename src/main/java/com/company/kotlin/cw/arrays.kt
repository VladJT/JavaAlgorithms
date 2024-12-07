package com.company.kotlin.cw

//Example 1:
//
//Input: nums = [1,2,3]
//Output: 2
//Explanation:
//Only two moves are needed (remember each move increments or decrements one element):
//[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
//Example 2:
//
//Input: nums = [1,10,2,9]
//Output: 16

fun main(args: Array<String>) {
    val startTime = System.currentTimeMillis()
    //-------------------------
    //    testing(movie(500, 15, 0.9), 43)
    //    testing(movie(100, 10, 0.95), 24)
    println(movie(500, 15, 0.9))
    println(evenNumbers(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 3))

    //-------------------------
    println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
}

fun minMoves2(nums: IntArray): Int {
    nums.sort()
    val median = nums[(nums.size.toDouble() / 2).toInt()]
    return nums.sumOf {
        Math.abs(it - median)
    }
}

fun movie(card: Int, ticket: Int, perc: Double): Int {
    var counter = 1
    var currentPerc: Double = 0.0
    var discountPrice: Double = 0.0
    var totalCardPrice = card + discountPrice
    while (ticket * counter < totalCardPrice) {
        counter++
        if (currentPerc == 0.0) currentPerc = perc
        else currentPerc *= perc
        totalCardPrice += ticket * currentPerc
    }
    return counter
}

fun getTicketSum(ticket: Double, perc: Double, counter: Int): Double {
    if (counter == 0) return 0.0
    return ticket * perc + getTicketSum(ticket * perc, perc, counter - 1)
}

fun evenNumbers(array: List<Int>, number: Int): List<Int> {
    var a = array.reversed();
    val result = mutableListOf<Int>()
    var counter =0;
    for(i in 0 until a.lastIndex){
        if(a[i]%2==0){
            result.add(a[i])
            counter++
        }
        if(counter==number) break
    }
    return result.reversed()
}

