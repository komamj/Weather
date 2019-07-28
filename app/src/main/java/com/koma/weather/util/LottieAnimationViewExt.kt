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

package com.koma.weather.util

import androidx.annotation.RawRes
import com.airbnb.lottie.LottieAnimationView
import com.koma.weather.R
import com.koma.weather.data.entities.Now

fun LottieAnimationView.setWeather(now: Now.NowBase) {
    @RawRes
    val rawRes = when (now.condCode) {
        //晴
        100 -> {
            R.raw.clear_day
        }
        //多云
        101, 102 -> {
            R.raw.cloudy_weather
        }
        //晴间多云
        103 -> {
            R.raw.mostly_cloudy
        }
        //阴天
        104 -> {
            R.raw.few_clouds
        }
        //阴天
        in 200..213 -> {
            R.raw.broken_clouds
        }
        //阵雨
        in 300..304 -> {
            R.raw.shower_rain
        }
        //雨
        in 305..309, in 313..315, 399 -> {
            R.raw.rainy_weather
        }
        //暴雨
        in 310..312, in 316..318 -> {
            R.raw.storm_weather
        }
        //雪
        in 400..499 -> {
            R.raw.snow_weather
        }
        //
        in 500..515 -> {
            R.raw.broken_clouds
        }
        else -> {
            R.raw.unknown
        }
    }
    this.setAnimation(rawRes)
}