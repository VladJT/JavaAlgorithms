package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import kotlin.random.Random


/**
 *        Maybe
Maybe подходит, если нас устраивает как наличие значения, так и его отсутствие.
Например, при обработке авторизации с возможностью гостевого доступа. В этом случае нас устроит
и наличие, и отсутствие авторизованного пользователя.
Можно сказать, что он где-то между Completable и Single. Его MaybeObserver, помимо onError и
Subscribe, имеет методы onSuccess и onComplete. Оба считаются «терминальными», и,
соответственно, взаимоисключающими. Если значение есть, то вызывается первый, наоборот — второй
 */
fun main() {
    getObservable()
        .subscribe(
            { println(it) },  // Обработка каждого значения
            { println("$it - error") },  // Обработка ошибок
            { println("complete!") }  // Завершение
        )
}

private fun getObservable() = Observable.create<Int> { emitter ->
    try {
        var randomValue: Int
        while (true) {
            randomValue = Random.nextInt(10)
            if (randomValue != 0) {
                emitter.onNext(randomValue)
                Thread.sleep(500)// Отправляем значения до тех пор, пока не 0
            } else {
                emitter.onComplete()  // Завершаем, когда значение 0
                break
            }
        }
    } catch (e: Exception) {
        emitter.onError(e)
    }
}