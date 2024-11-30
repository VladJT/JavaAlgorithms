package com.company.kotlin.cw

import kotlin.math.round

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
    val arr1 = intArrayOf(1, 2, 3)
    val arr2 = intArrayOf(1, 10, 2, 9)
    println(minMoves2(arr1))
    println(minMoves2(arr2))

    //-------------------------
    println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
}

fun minMoves2(nums: IntArray): Int {
    nums.sort()
    val median = nums[(nums.size.toDouble()/ 2).toInt()]
    return nums.sumOf {
        Math.abs(it - median)
    }
}


