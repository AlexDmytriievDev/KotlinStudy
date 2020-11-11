package com.example.kotlinstudy.utils

import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RxUtils @Inject internal constructor() {

    fun <T> zipWithTimer(single: Single<T>, secondsDelay: Int): Single<T> {
        return Single.zip(
            Single.timer(secondsDelay.toLong(), TimeUnit.SECONDS), single,
            { _: Long?, originalValue: T -> originalValue })
    }
}