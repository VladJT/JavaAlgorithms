package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

// observable

fun main() {
    //Consumer(Producer()).execLambda()

//    val observer = Consumer(Producer())
//    observer.execTimeFlow()
//    readln()

    val o: MyObservable = MyObservable()
    val p = o.getData().subscribe(
        {
            println(it)
        }, { e ->
            println("error: $e")
        }, {
            println("finished")
        })

}

class MyObservable {

    fun getData(): Observable<Int> {
        return Observable.create<Int> { emitter ->
            try {
                emitter.onNext(1)
                Thread.sleep(500)
                emitter.onNext(2)
                emitter.onError(Throwable(Exception("some errors")))
                Thread.sleep(500)
                emitter.onNext(3)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}

// в Producer мы станем создавать Observable разными способами
class Producer {
    fun getFlow(): Observable<String> {
        return Observable.fromIterable(listOf("1", "2", "3"))
    }

    // Последовательно выдаст числа от первого аргумента до второго. В нашем случае — от 1 до 10
    fun range() = Observable.range(1, 10)


    //В этом примере Observable будет бесконечно выдавать Long-числа начиная с 0, с интервалом 5
    //секунд. Подписываемся, используя лямбду, чтобы не создавать отдельный Observer
    fun getTimeFlow(): Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }


    fun randomResultOperation(): Boolean {
        Thread.sleep(Random.nextLong(1000))
        return listOf(true, false, true)[Random.nextInt(2)]
    }

    fun fromCallable() = Observable.fromCallable {
        //Симуляция долгих вычислений
        val result = randomResultOperation()
        return@fromCallable result
    }

    fun create() = Observable.create<String> { emitter ->
        try {
            for (i in 0..10) {
                randomResultOperation().let {
                    if (it) {
                        emitter.onNext("Success$i")
                    } else {
                        emitter.onError(RuntimeException("Error"))
                        return@create
                    }
                }
            }
            emitter.onComplete()
        } catch (t: Throwable) {
            emitter.onError(RuntimeException("Error"))
        }
    }
}


// в Consumer — на них подписываться
class Consumer(val producer: Producer) {
    val stringObserver = object : Observer<String> {
        //Disposable — интерфейс, реализация которого напрямую связана с потоком данных (источником).
        //Этот интерфейс позволяет отписаться от источника посредством вызова у него метода dispose().
        //Например, пользователь закрыл экран, на котором отображались данные из потока. Данные больше
        //не требуются, надо отписаться от источника и прекратить обработку данных из потока. Чтобы
        //добиться этого, предварительно сохраняем Disposable, полученный в onSubscribe, а затем просто
        //вызываем у него dispose(), например, где-нибудь на этапе onPause.
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

    // мы просто используем соответствующую версию функции subscribe, которая
    //принимает на вход три лямбды и возвращает Disposable.
    fun execLambda() {
        producer.getFlow().subscribe({ s ->
            println("onNext: $s")
        }, { e ->
            println("onError: ${e.message}")
        }, {
            println("onComplete")
        })
    }

    fun execTimeFlow() {
        producer.getTimeFlow().subscribe({ s ->
            println("onNextTime: $s")
        })
    }

    fun execJust() {
        producer.getFlow().subscribe(stringObserver)
    }
}
