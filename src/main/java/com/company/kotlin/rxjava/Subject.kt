package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


/**
 * Subject представляет собой класс, который расширяет (наследует) Observable и реализует интерфейс
Observer. Это одновременно и Observable, и Observer. То есть он может подписываться на источник
данных и рассылать эти данные своим подписчикам. Ещё такой класс используется как некий
источник на ручном приводе, вручную вызывая onNext, когда это требуется. Subject крайне полезны,
например, когда надо быстро связать между собой реактивный и нереактивный код, так как позволяют
сделать это без массивного рефакторинга нереактивного кода в реактивный. Достаточно просто
создать Subject, выставить его наружу из нереактивного класса и изнутри передавать в него значения
по мере работы. Так можно, например, оборачивать в Rx библиотеки, асинхронный код которых
передаёт свой результат через колбэки.
Рассмотрим самый простой Subject — PublishSubject.

BehaviorSubject хранит только последнее значение.
Это то же самое, что и ReplaySubject, но с буфером размером 1.
Во время создания ему может быть присвоено начальное значение, таким образом гарантируя,
что данные всегда будут доступны новым подписчикам
 */

fun main() {
    publishSubject.subscribe { println("on next: $it") }

    Thread.sleep(2000)
    publishSubject.onNext("from main!")

    readln()
}

val publishSubject = PublishSubject.create<String>().apply {
    Observable.interval(1, TimeUnit.SECONDS).subscribe() {
        println("value from subject $it")
    }
}
