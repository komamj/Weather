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
import com.koma.weather.util.Constants

open class Base {
    @SerializedName("basic")
    var basic: Basic? = null
    @SerializedName("update")
    var update: Update? = null
    @SerializedName("status")
    var status: String = Constants.OK

    data class Basic(
        @SerializedName("cid")
        val cid: String,
        @SerializedName("location")
        val location: String,
        @SerializedName("parent_city")
        val parentCity: String,
        @SerializedName("admin_area")
        val admin_area: String,
        @SerializedName("cnty")
        val country: String,
        @SerializedName("lon")
        val lon: String,
        @SerializedName("tz")
        val tz: String
    )

    data class Update(
        //当地时间，24小时制，格式yyyy-MM-dd HH:mm
        @SerializedName("loc")
        val loc: String,
        //UTC时间，24小时制，格式yyyy-MM-dd HH:mm
        @SerializedName("utc")
        val utc: String
    )
}