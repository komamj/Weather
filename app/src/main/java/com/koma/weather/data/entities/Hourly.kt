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

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("hourly")
    val hourly: List<HourlyBase>
) {
    data class HourlyBase(
        //预报时间，格式yyyy-MM-dd hh:mm
        @SerializedName("time")
        val time: String,
        //温度
        @SerializedName("tmp")
        val tmp: String,
        //天气状况代码
        @SerializedName("cond_code")
        val condCode: Int,
        //天气状况描述
        @SerializedName("cond_txt")
        val condTxt: String
    )
}