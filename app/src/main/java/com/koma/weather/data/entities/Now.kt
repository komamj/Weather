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

data class Now(
    @SerializedName("now")
    val now: NowBase
) : Base() {
    data class NowBase(
        //体感温度，默认单位：摄氏度
        @SerializedName("fl")
        val fl: String,
        //温度，默认单位：摄氏度
        @SerializedName("tmp")
        val tmp: String,
        //实况天气状况代码
        @SerializedName("cond_code")
        val condCode: Int,
        //实况天气状况描述
        @SerializedName("cond_txt")
        val condTxt: String,
        //风向360角度
        @SerializedName("wind_deg")
        val windDeg: String,
        //风向
        @SerializedName("wind_dir")
        val windDir: String,
        //风力
        @SerializedName("wind_sc")
        val windSc: String,
        //风速，公里/小时
        @SerializedName("wind_spd")
        val windSpd: String,
        //相对湿度
        @SerializedName("hum")
        val hum: String,
        //降水量
        @SerializedName("pcpn")
        val pcpn: String,
        //大气压强
        @SerializedName("pres")
        val pres: String,
        @SerializedName("vis")
        val vis: String,
        //云量
        @SerializedName("cloud")
        val cloud: String
    )
}