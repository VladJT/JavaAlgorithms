package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

private val data = listOf(1, 4, 6, 98, 0, 1, 1, 4)
val names: Observable<String> =
    Observable.just("Nick", "Tim", "Vlad").delay(Random.nextLong(2000), TimeUnit.MILLISECONDS)
val emails: Observable<String> = Observable.just("ni@mail.rt", "tim@list.ru", "vlad@hotmail.com")
    .delay(Random.nextLong(2000), TimeUnit.MILLISECONDS)


fun main() {
    println("*** RxJava ***")
//    simpleObservable()
//    testJust()
//
//    val o = MyProducer()
//
//    MyObserver(
//        o.getIterable().take(7)//берем первые 7 элемента
//            .skip(2)//пропускаем первые 2 элемента
//            .filter { it < 90 }.distinct() // только уникальные элементы
//            .distinctUntilChanged()//убирает повтор.данные, идущие друг за другом
//            .map(::mapToListAndAdd)
//    ).exec()
//    //MyObserver(o.getRange()).execInOneRow()
//
//    MyObserver(o.fromCallable()).exec()
//
//    testZipResult()
//    testMergeResult()
//    testFlatmap()
//
//    Single.create<String> {
//        it.onSuccess("my_text")
//    }.subscribeByDefault()
//        .delay(1000, TimeUnit.MILLISECONDS)
//        .subscribe({
//            println("get $it")
//        }, {})

    //  println("SOURCES")
    //  Sources().exec()

    testDisposable()
    readln()
}

fun testDisposable() {
    println("DISPOSABLE")
    val disposable: Disposable = Observable.interval(1, TimeUnit.SECONDS)
        .subscribe({ println("next: $it") },//on next
            { println("Ошибка: ${it.message}") },//on error
            { println("==Готово==") })//on complete
    Thread.sleep(5000)
    disposable.dispose()
}


fun <T> Single<T>.subscribeByDefault(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
    //  .observeOn(AndroidSchedulers.mainThread())// для ANDROID
}

fun testFlatmap() {
    println("*** testFlatmap ***")
    names.flatMap { name ->
        return@flatMap getUserInfo(name)
    }.subscribe { println(it) }
}

fun getUserInfo(name: String): Observable<List<String>> {
    return Observable.just(listOf(name, "email.com"))
}

fun testMergeResult() {
    val first = Observable.just(1, 2, 3)
    val second = Observable.just(4, 5, 6)
    first.mergeWith(second).subscribe({ s ->
        println("onNext: $s")
    }, {
        println("onError: ${it.message}")
    })
}

/**
 * Оператор zip позволяет подписаться на несколько источников параллельно и получать их значения
 * одновременно. Это удобно, например, когда требуется сделать несколько сетевых вызовов параллельно
 * Zip дождался значения от каждого источника, после чего отдал их нам в качестве аргументов.
 * Возникает вопрос: что, если наши источники будут излучать разное количество значений? Всё просто.
 * Функция не будет вызываться до тех пор, пока не появится значение от каждого из источника.
 */
fun testZipResult() {
    val startTime = System.currentTimeMillis()
    println("Start loading names & e-mails...")
    Observable.zip(names, emails) { name, email ->
        return@zip listOf<String>(name, email)
    }.subscribe({
        println("${it[0]} : ${it[1]}")
    }, {}, {
        println("Complete loading names & e-mails at: ${System.currentTimeMillis() - startTime}ms")
    })
}

private fun mapToListAndAdd(value: Int): List<Int> = listOf(value, 8)

class MyObserver(private val observable: Observable<*>) {
    fun exec() {
        println("$observable")
        observable.subscribe({ println("next: $it") },//on next
            { println("Ошибка: ${it.message}") },//on error
            { println("==Готово==") })//on complete
    }

    fun execInOneRow() {
        println("$observable")
        observable.subscribe({ print("$it, ") },//on next
            { println("Ошибка: ${it.message}") },//on error
            { println("==Готово==") })//on complete
    }
}

class MyProducer {
    fun getInterval(): Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
    fun getIterable(): Observable<Int> = Observable.fromIterable(data)
    fun getRange(): Observable<Int> = Observable.range(1000, 1100)

    fun fromCallable(): Observable<Boolean> = Observable.fromCallable {
        return@fromCallable randomBoolean()
    }


    private fun randomBoolean(): Boolean {
        Thread.sleep(Random.nextLong(3000))
        return listOf(true, false, true)[Random.nextInt(2)]
    }
}


fun testJust() {
    val observable = Observable.just("first", "second", "third")
    observable.subscribe({ println("next: $it") },//on next
        { println("Ошибка: ${it.message}") },//on error
        { println("==Готово==") })//on complete
}


fun simpleObservable() {
    /**
     * Самый мощный оператор создания источника. Оператор create предполагает полностью ручное
    управление событиями, которые выдаёт источник, и мы в буквальном смысле вызываем onNext,
    onComplete и onError у подписчиков
     */
    val observable = Observable.create<Int> { emitter ->
        try {
            data.forEach { el -> emitter.onNext(el) }
        } catch (e: Throwable) {
            emitter.onError(e)
        }
        emitter.onComplete()
    }

    val observer = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            println("Подписан $d ${d.hashCode()}")
        }

        override fun onNext(t: Int) {
            print("*next: $t, ")
        }

        override fun onError(e: Throwable) {
            println("Ошибка: ${e.message}")
        }

        override fun onComplete() {
            println("==Готово==")
        }

    }
    observable.subscribe(observer)
}
