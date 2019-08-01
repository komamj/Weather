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

package com.koma.weather.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.koma.weather.base.BaseViewModel
import com.koma.weather.data.entities.*
import com.koma.weather.data.source.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : BaseViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _now = MutableLiveData<Now.NowBase>()
    val now: LiveData<Now.NowBase>
        get() = _now

    private val _forecast = MutableLiveData<List<Forecast.ForecastBase>>()
    val forecast: LiveData<List<Forecast.ForecastBase>>
        get() = _forecast

    private val _lifestyle = MutableLiveData<List<Lifestyle.LifeStyleBase>>()
    val lifestyle: LiveData<List<Lifestyle.LifeStyleBase>>
        get() = _lifestyle

    private val _hourly = MutableLiveData<List<Hourly.HourlyBase>>()
    val hourly: LiveData<List<Hourly.HourlyBase>>
        get() = _hourly

    init {
        _isLoading.value = true
    }

    fun getWeatherNow(city: City) {
        val disposable = repository.getWeatherNow(city.name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _now.value = it.now
                    Timber.d("successful ${it.now}")
                },
                {
                    Timber.e("error ${it.message}")
                },
                {
                    _isLoading.value = false
                }
            )
        disposables.add(disposable)
    }

    fun getWeatherForecast(city: City) {
        val disposable = repository.getWeatherForecast(city.name)
            .map {
                it.dailyForecast
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _forecast.value = it
                },
                {
                    Timber.e("error ${it.message}")
                },
                {
                    _isLoading.value = false
                })
        disposables.add(disposable)
    }
}