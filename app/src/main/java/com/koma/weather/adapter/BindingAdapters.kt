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

package com.koma.weather.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.koma.weather.R
import org.joda.time.format.DateTimeFormat

@BindingAdapter("isRefreshing")
fun bindIsRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean) {
    view.post {
        view.isRefreshing = isRefreshing
    }
}

@BindingAdapter("week")
fun bindDate(view: TextView, date: String) {
    val dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
    val dateTime = dateTimeFormatter.parseDateTime(date)
    dateTime.dayOfWeek()
    view.text = dateTime.dayOfWeek().asText
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, condCode: Int) {
    @DrawableRes
    val drawableRes = when (condCode) {
        //晴
        100 -> {
            R.mipmap.ic_clear_day
        }
        //多云
        101, 102 -> {
            R.mipmap.ic_cloudy_weather
        }
        //晴间多云
        103 -> {
            R.mipmap.ic_mostly_cloudy
        }
        //阴天
        104 -> {
            R.mipmap.ic_few_clouds
        }
        //阴天
        in 200..213 -> {
            R.mipmap.ic_broken_clouds
        }
        //阵雨
        in 300..304 -> {
            R.mipmap.ic_shower_rain
        }
        //雨
        in 305..309, in 313..315, 399 -> {
            R.mipmap.ic_rainy_weather
        }
        //暴雨
        in 310..312, in 316..318 -> {
            R.mipmap.ic_storm_weather
        }
        //雪
        in 400..499 -> {
            R.mipmap.ic_snow_weather
        }
        //
        in 500..515 -> {
            R.mipmap.ic_broken_clouds
        }
        else -> {
            R.mipmap.ic_unknown
        }
    }
    Glide.with(view.context)
        .load(drawableRes)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)
}
