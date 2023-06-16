package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

//перейдём к операторам манипуляции над потоком
class Operators {

    fun exec() {

        val observable = Producer()
        val observer = Consumer(observable)
        println("\nexecTake")
        observer.execTake()

        println("\nexecSkip")
        observer.execSkip()

        println("\nexecMap")
        observer.execMap()

        println("\nexecDistinct")
        observer.execDistinct()

        println("\nexecFilter")
        observer.execFilter()

        println("\nexecMerge")
        observer.execMerge()

        println("\nexecFlatMap")
        observer.execFlatMap()

        println("\nexecZip")
        observer.execZip()
    }

    class Producer {
        fun createJust() = Observable.just("1", "2", "3", "3")
        fun createJust2() = Observable.just("4", "5", "6")
    }


    class Consumer(val producer: Producer) {
        //Оператор take(count) берёт первые count элементов цепочки
        fun execTake() {
            producer.createJust()
                .take(2)
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        // В противовес оператору take skip позволяет пропустить несколько первых элементов
        fun execSkip() {
            producer.createJust()
                .skip(2)
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        //оператор преобразует элементы цепочки согласно переданному ему правилу. Чаще - по лямбде
        fun execMap() {
            producer.createJust()
                .map { "-" + it + "-" }
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }


        // Оператор distinct отсеивает дубликаты.
        fun execDistinct() {
            producer.createJust()
                .distinct()
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execFilter() {
            producer.createJust().filter() { it.toInt() >= 2 }.subscribe() { s ->
                println("onNext: $s")
            }
        }

        fun execMerge() {
            producer.createJust()
                .mergeWith(producer.createJust2())
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        // Оператор flatMap похож на map, также применяет функцию к каждому излучаемому элементу, но эта
        //функция возвращает Obsevable. То есть один излучаемый элемент исходного источника через flatMap
        //порождает другие. Проще говоря, flatMap из каждого элемента создаёт новый источник, после чего
        // выполняет слияние этих источников, похожее на применение над ними оператора merge.
        fun execFlatMap() {
            producer.createJust()
                .flatMap {
                    val delay = Random.nextInt(1000).toLong()
                    return@flatMap Observable.just(it + "x").delay(
                        delay,
                        TimeUnit.MILLISECONDS
                    )
                }
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        // Оператор zip позволяет подписаться на несколько источников параллельно и получать их значения
        //одновременно. Это удобно, например, когда требуется сделать несколько сетевых вызовов
        //параллельно
        //Zip дождался значения от каждого источника, после чего отдал их нам в качестве аргументов.
        //Возникает вопрос: что, если наши источники будут излучать разное количество значений? Всё просто.
        //Функция не будет вызываться до тех пор, пока не появится значение от каждого из источника.
        fun execZip() {
            val observable1 = Observable.just("1").delay(1, TimeUnit.SECONDS)
            val observable2 = Observable.just("2").delay(2, TimeUnit.SECONDS)
            val observable3 = Observable.just("3").delay(4, TimeUnit.SECONDS)
            val observable4 = Observable.just("4").delay(6, TimeUnit.SECONDS)

            Observable.zip(observable1, observable2, observable3, observable4,
                object : Function4<String, String, String, String, List<String>> {
                    override fun invoke(
                        t1: String,
                        t2: String,
                        t3: String,
                        t4: String
                    ): List<String> {
                        return listOf(t1, t2, t3, t4)
                    }
                })
                .subscribeOn(Schedulers.computation())
                .subscribe({
                    println("Zip result: $it")
                }, {
                    println("onError: ${it.message}")
                })
        }

    }
}
