package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


/**
 * Flowable аналогичен Observable и отличается от него поддержкой механизма BackPressure.
Назначение BackPressure — корректная обработка значений, выдаваемых источником, когда их
настолько много, что код не успевает их обработать. Например, если мы получаем частые сообщения
из сетевого сокета. Такая ситуация может привести к тому, что неуспевающие обработчики «съедят»
всю память, а приложение упадёт с OutOfMemoryException. BackPressure позволяет распорядиться
теми значениями, которые выдаёт источник, когда обработка предыдущих ещё не завершилась. Есть
несколько стратегий, применяемых через соответствующие операторы:
1. onBackpressureBuffer() — сохранять значения и обрабатывать по мере возможности. В
качестве аргумента обычно передаётся максимальный размер буфера.
2. onBackpressureDrop() — выбрасывать лишние значения.
3. onBackpressureLatest() — выбрасывать все значения, кроме последнего.

 */
class Flowable {
    class BackPressure {
        fun exec() {
            val producer = Producer()
            val consumer = Consumer(producer)
            consumer.consume()
        }
    }


    class Producer {
        fun noBackPressure() = Observable.range(0, 10000000).subscribeOn(Schedulers.io())

        fun backPressure() = io.reactivex.rxjava3.core.Flowable
            .range(0, 10000000).subscribeOn(Schedulers.io()).onBackpressureDrop()
    }

    class Consumer(val producer: Producer) {

        fun consume() {
            consumeBackPressure()
        }

        fun consumeNoBackPressure() {
            producer.noBackPressure()
                .observeOn(Schedulers.computation())
                .subscribe({
                    Thread.sleep(1000)
                    println(it.toString())
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun consumeBackPressure() {
            producer.backPressure()
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe({
                    Thread.sleep(1000)
                    println(it.toString())
                }, {
                    println("onError: ${it.message}")
                })
        }
    }
}