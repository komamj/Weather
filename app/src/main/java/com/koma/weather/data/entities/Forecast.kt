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

package com.koma.weather.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Forecast(
    @SerializedName("daily_forecast")
    val dailyForecast: List<ForecastBase>
) {
    @Keep
    data class ForecastBase(
        //预报日期
        @SerializedName("date")
        val date: String,
        //最高温度
        @SerializedName("tmp_max")
        val tmpMax: String,
        //最低温度
        @SerializedName("tmp_min")
        val tmpMin: String,
        //白天天气状况代码
        @SerializedName("cond_code_d")
        val condCodeD: Int,
        //夜间天气状况代码
        @SerializedName("cond_code_n")
        val condCodeN: Int,
        //白天天气状况描述
        @SerializedName("cond_txt_d")
        val condTxtD: String,
        //晚间天气状况描述
        @SerializedName("cond_txt_n")
        val condTxtN: String
    )
}