package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

fun main() {
    CompositeDisposableEx().execComposite()
}

class CompositeDisposableEx {
    fun observable1() = Observable.just("1")
    fun observable2() = Observable.just("2")

    fun execComposite() {
        val compositeDisposable = CompositeDisposable()
        val disposable1 = observable1().subscribe {
            println(it)
        }
        val disposable2 = observable1().subscribe {
            println(it)
        }
        compositeDisposable.addAll(disposable1)
        compositeDisposable.addAll(disposable2)
    }

}