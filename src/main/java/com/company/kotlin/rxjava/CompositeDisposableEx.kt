package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

fun main() {
    CompositeDisposableEx().execComposite()
}

class TestObs {
    fun getData(): Observable<Int> {
        return Observable.create<Int> { emitter ->
            try {
                emitter.onNext(1)
                Thread.sleep(500)
                emitter.onNext(2)
                //   emitter.onError(Throwable(Exception("some errors")))
                Thread.sleep(500)
                emitter.onNext(3)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}

class CompositeDisposableEx {
    fun observable1() = TestObs().getData();
    fun observable2() = Observable.just(97, 98, 99)

    fun execComposite() {
        val compositeDisposable = CompositeDisposable()
        val disposable1 = observable1().zipWith(observable2()) { z1, z2 -> z1 + z2 }.subscribe {
            println(it)
        }

        compositeDisposable.addAll(disposable1)

    }
}