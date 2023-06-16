package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


/**
 * Горячий Observable отправляет данные независимо от того, подписан кто-нибудь на него или нет.
Если никто его в это время не слушает, данные будут потеряны. Такой подход удобен, например, для
оборачивания сокетных соединений, так как данные там идут непрерывно.

 */

fun main() {
    val hotObservable = createHotObservable()
    hotObservable.connect()
    Thread.sleep(2000)

    hotObservable.subscribe { println("🏳️‍ = $it") }
    hotObservable.connect()

    Thread.sleep(2000)

    hotObservable.subscribe { println("🧑 = $it") }
    hotObservable.connect()

    readln()
}

fun createHotObservable() = Observable.interval(1000, TimeUnit.MILLISECONDS).replay()

/**
Replay
Отличие в том, что метод кэширует данные, и каждый новый подписчик получает полный набор
данных, например, все сообщения чата, пришедшие до подписки, когда бы он ни подключился. Можно
подумать, что таким образом мы получаем обычный холодный Observable, но это не так. Наш
источник всё ещё горячий и его работа начнётся только после вызова метода connect().


RefCount
Этот метод сделает из ConnectableObservable Observable, который начнёт свою работу, как только
появится первый подписчик.
Здесь нет метода connect(), так как класс ConnectableObservable, полученный методом publish(),
обёрнут обратно в класс Observable, благодаря использованию метода refCount(). При такой
конструкции мы получаем горячий Observable, который ведёт себя как холодный. Тем не менее это
именно горячий Observable, так как он будет раздавать одни и те же данные всем подписчикам, а не
стартовать работу заново для каждого из них.


Cache
Observable, который появится в результате работы этого оператора, будет похож на результат
операторов replay() и refCount(). Он начинает работу при первом подписчике, хранит все элементы и
выдаёт их каждому новому подписчику, но не заканчивает работу при отсутствии подписчиков.

 */