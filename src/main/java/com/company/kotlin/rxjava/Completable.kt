package com.company.kotlin.rxjava

import io.reactivex.rxjava3.core.Completable

/**
 * Completable
Источник подходит, когда получать значения не требуется, а нас интересует сам факт завершения
какой-либо операции, например, запись в файл или базу данных. Для обработки этого источника
используется CompletableObserver, аналогичный Observer, но не имеющий onNext
 */
fun main() {
    saveCompletable("vlad@mail.ru").subscribe({
        println("Complete")
    },
        {
        println("on Error")
    })


}

private fun saveCompletable(mail: String) = Completable.create { emitter ->
    try {
        saveToDb(mail)
        throw Exception("not saved")
        emitter.onComplete()
    } catch (e: java.lang.Exception) {
        emitter.onError(e)
    }
}

fun saveToDb(mail: String) {
    println("Start save...")
    Thread.sleep(2000)
    println("Saved $mail")
}
