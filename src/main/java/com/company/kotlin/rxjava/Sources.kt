package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class Sources {
    fun exec() {
        val observable = Producer()
        val observers = Consumer(observable)
        //    println("\n--execCompletable--")
        //    observer.execCompletable()

        //    println("\n--execSingle--")
        //     observer.execSingle()

        println("\n--execMaybe--")
        observers.execMaybe()

        println("\n--execHotObservable--")
        observers.execHotObservable()

        println("\n--execPublishSubject--")
        observers.execPublishSubject()

    }

    class Producer {
        fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        // Completable
        //Источник подходит, когда получать значения не требуется, а нас интересует сам факт завершения
        //какой-либо операции, например, запись в файл или базу данных. Для обработки этого источника
        //используется CompletableObserver, аналогичный Observer, но не имеющий onNext
        fun completable() = Completable.create { emitter ->
            randomResultOperation().let { res ->
                if (res) {
                    emitter.onComplete()
                } else {
                    emitter.onError(RuntimeException("Error"))
                    return@create
                }
            }
        }

        //Single
        //Источник аналогичен Observable, однако может выдать только одно значение. Single идеально
        //подходит для HTTP-запросов, так как всегда ожидается только один ответ от сервера. Получение
        //значения и завершение его работы — одно и то же событие. Вместо onNext и onComplete у его
        //SingleObserver есть только один метод onSuccess, и он терминальный
        fun single() = Single.fromCallable {
            return@fromCallable "Some string value"
        }


        //Maybe
        //Maybe подходит, если нас устраивает как наличие значения, так и его отсутствие.
        //Например, при обработке авторизации с возможностью гостевого доступа. В этом случае нас устроит
        //и наличие, и отсутствие авторизованного пользователя.
        //Можно сказать, что он где-то между Completable и Single. Его MaybeObserver, помимо onError и
        //onSubscribe, имеет методы onSuccess и onComplete. Оба считаются «терминальными», и,
        //соответственно, взаимоисключающими. Если значение есть, то вызывается первый, наоборот — второй
        fun maybe() = Maybe.create { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onSuccess("💻")
                } else {
                    emitter.onComplete()
                    return@create
                }
            }
        }

        //Горячий Observable отправляет данные независимо от того, подписан кто-нибудь на него или нет
        fun hotObservable() =
            Observable.interval(1, TimeUnit.SECONDS)
                .publish()
        //Отличие в том, что метод кэширует данные, и каждый новый подписчик получает полный набор
        //данных, например, все сообщения чата, пришедшие до подписки, когда бы он ни подключился. Можно
        //подумать, что таким образом мы получаем обычный холодный Observable, но это не так. Наш
        //источник всё ещё горячий и его работа начнётся только после вызова метода connect().
        // .replay()

        fun publishSubject() = PublishSubject.create<String>().apply {
            Observable.timer(2, TimeUnit.SECONDS)
                .subscribe {
                    onNext("Value from subject")
                }
        }
    }//Producer


    class Consumer(val producer: Producer) {
        private val disposables = CompositeDisposable()

        fun exec() {
        }

        fun execCompletable() {
            producer.completable()
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        println("onSubscribe")
                    }

                    override fun onComplete() {
                        println("onComplete")
                    }

                    override fun onError(e: Throwable) {
                        println("onError ${e.message}")
                    }
                })
        }


        fun execSingle() {
            producer.single()
                .map { it + it }
                .subscribe({ s ->
                    println("onSuccess: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execMaybe() {
            producer.maybe()
                .subscribe({ s ->
                    println("onSuccess: $s")
                }, {
                    println("onError: ${it.message}")
                }, {
                    println("onComplete")
                })
        }

        fun execHotObservable() {
            val hotObservable = producer.hotObservable()
            hotObservable.subscribe {
                println("🐨‍ observer - $it")
            }
            hotObservable.connect()//этот метод запускает работу Observable. При этом не имеет значения, есть подписчики или нет
            Thread.sleep(3000)
            hotObservable
                .subscribe {
                    println("😶️ observer - $it")
                }
            Thread.sleep(3000)

        }

        fun execPublishSubject() {
            val subject = producer.publishSubject()
            subject
                .subscribe({
                    println("onNext: $it")
                }, {
                    println("onError: ${it.message}")
                })
            subject.onNext("from exec")
        }
    }//Consumer
}
