package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun main() {
    val items = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    items.switchMap<Int> { s ->
        var delay = Random.nextInt(3).toLong()
        Observable.just(s * 2)
            .delay(delay, TimeUnit.SECONDS, Schedulers.io());
    }.subscribe {
        println(it)
    }
    readln()
}
