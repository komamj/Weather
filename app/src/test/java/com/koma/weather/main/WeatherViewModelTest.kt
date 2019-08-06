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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.koma.weather.data.entities.City
import com.koma.weather.data.entities.Now
import com.koma.weather.data.source.WeatherRepository
import com.koma.weather.util.RxJavaRule
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class WeatherViewModelTest {
    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxJavaRule = RxJavaRule()

    private val repository = mock(WeatherRepository::class.java)

    private val viewModel: WeatherViewModel = WeatherViewModel(repository)

    private lateinit var city: City

    @Before
    fun init() {
        city = City("0000", "深圳")
    }

    @Test
    fun getNow() {
        `when`(repository.getWeatherNow(city.name))
            .thenReturn(Observable.error {
                Throwable("获取天气数据出错")
            })
        viewModel.getWeatherNow(city)
        verify(repository).getWeatherNow(city.name)
    }

    @Test
    fun getForecast() {
    }

    @Test
    fun getHourly() {
    }

    @Test
    fun getWeatherNow() {
    }

    @Test
    fun getWeatherForecast() {
    }
}