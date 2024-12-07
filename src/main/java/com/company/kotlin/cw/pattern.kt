package com.company.kotlin.cw

class ListNode(var value: Int) {
    var next: ListNode? = null
}




fun main(args: Array<String>) {
    val startTime = System.currentTimeMillis()
    //-------------------------

    //2,4,3], l2 = [5,6,4]
    val nodes1 = listOf(ListNode(2),ListNode(4),ListNode(3), )
    val nodes2 = listOf(ListNode(5),ListNode(6),ListNode(4), )
    nodes1[0].next = nodes1[1]
    nodes1[1].next = nodes1[2]
    nodes2[0].next = nodes2[1]
    nodes2[1].next = nodes2[2]


    addTwoNumbers(nodes1[0], nodes2[0])

    //-------------------------
    println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime))
}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val number1 = getNodeAsString(l1).reversed().toInt()
    val number2 = getNodeAsString(l2).reversed().toInt()

    var prevLink : ListNode? = null
    var newListNode: ListNode? = null
    (number1+number2).toString().forEach { s->
        newListNode = ListNode(s.toString().toInt())
        if(prevLink!=null){
            newListNode?.next = prevLink
        }
        prevLink = newListNode
    }
    return newListNode
}

fun getNodeAsString(l: ListNode?): String{
    if(l==null) return ""
    return l.value.toString() + getNodeAsString(l.next)
}

fun printNode(l: ListNode?){
    if(l==null) println()
    else {
        print(l.value)
        printNode(l.next)
    }
}


