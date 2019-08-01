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

import com.koma.weather.data.entities.Forecast
import com.koma.weather.data.entities.Hourly
import com.koma.weather.data.entities.Lifestyle
import com.koma.weather.data.entities.Now
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val webService: WebService) : IRemoteDataSource {
    override fun getWeatherNow(location: String): Observable<Now> {
        return webService.getNow(location)
            .map {
                it.weather[0]
            }
    }

    override fun getWeatherHourly(location: String): Observable<Hourly> {
        return webService.getWeatherHourly(location)
            .map {
                it.weather[0]
            }
    }

    override fun getWeatherForecast(location: String): Observable<Forecast> {
        return webService.getWeatherForecast(location).map {
            it.weather[0]
        }
    }

    override fun getWeatherLifestyle(location: String): Observable<Lifestyle> {
        return webService.getWeatherLifestyle(location)
            .map { it.weather[0] }
    }
}