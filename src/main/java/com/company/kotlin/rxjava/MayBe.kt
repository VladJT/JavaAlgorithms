package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Maybe
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
    getMayBe().subscribe({ println(it) },
        { println("$it - error") },
        { println("complete! ") })
}

private fun getMayBe() = Maybe.create<Boolean> { emitter ->
    try {
        val success = Random.nextInt(3)
        when (success) {
            0 -> emitter.onSuccess(false)
            1 -> emitter.onSuccess(true)
            2 -> emitter.onComplete()
        }
    } catch (e: Exception) {
        emitter.onError(e)
    }
}