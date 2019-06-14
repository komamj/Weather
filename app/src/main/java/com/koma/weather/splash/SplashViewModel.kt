/*
 * Copyright 2019 JUN MAO
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.koma.weather.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    private val _time = MutableLiveData<Long>()

    val time: LiveData<Long> = _time

    init {
        _time.value = MAX_COUNT
    }

    fun startCountDown() {
        val disposable =
            Flowable.intervalRange(1, MAX_COUNT, 1, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = {
                    _time.value = 3 - it
                },
                    onError = {},
                    onComplete = {})
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()

        disposables.clear()
    }

    companion object {
        const val MAX_COUNT = 5L
    }
}