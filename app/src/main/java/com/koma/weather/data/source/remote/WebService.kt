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

package com.koma.weather.data.source.remote

import com.koma.weather.data.entities.HeWeather6
import com.koma.weather.data.entities.Lifestyle
import com.koma.weather.data.entities.Now
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 天气api
 */
interface WebService {
    /**
     * 获取实况天气
     * @param location 位置
     */
    @GET("now")
    fun getNow(@Query("location") location: String): Observable<HeWeather6<Now>>

    /**
     * 获取3-10天预报
     * @param location 位置
     */
    @GET("forecast")
    fun getForecast(@Query("location") location: String)

    /**
     * 获取逐小时预报
     * @param location 位置
     */
    @GET("hourly")
    fun getHourly(@Query("location") location: String)

    /**
     * 获取生活指数
     * @param location 位置
     */
    @GET("lifestyle")
    fun getLifestyle(@Query("location") location: String): Observable<HeWeather6<Lifestyle>>
}