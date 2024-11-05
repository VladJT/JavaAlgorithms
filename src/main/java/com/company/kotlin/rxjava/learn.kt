package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun createFlow() = Observable.create<String> { emitter ->
    try {
        for (i in 1..10) {
            Thread.sleep(300)
            if (i % 3 != 0) {
                emitter.onNext("Success$i")
            } else {
             //   emitter.onError(RuntimeException("Error"))
            }
        }
        emitter.onComplete()
    } catch (t: Throwable) {
        emitter.onError(RuntimeException("Error"))
    }
}


val stringObserver = object : Observer<String> {
    var disposable: Disposable? = null

    override fun onSubscribe(d: Disposable) {
        disposable = d
        println("onSubscribe")
    }

    override fun onNext(t: String) {
        println("onNext: $t")
    }

    override fun onError(e: Throwable) {
        println("onError: ${e.message}")
    }

    override fun onComplete() {
        println("on complete")
    }
}

fun main() {
   // createFlow().observeOn(Schedulers.io()).subscribe(stringObserver)
    io.reactivex.rxjava3.core.Flowable
        .range(0, 10000000).onBackpressureBuffer().subscribe{
            println(it)
        }
}