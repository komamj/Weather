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

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.koma.weather.R
import com.koma.weather.base.BaseAdapter
import com.koma.weather.data.entities.Forecast
import com.koma.weather.databinding.ItemForecastBinding

class ForecastAdapter(context: Context, callback: ForecastDiffCallback = ForecastDiffCallback()) :
    BaseAdapter<Forecast.ForecastBase, ForecastAdapter.ForecastVH, ForecastAdapter.ForecastDiffCallback>(
        callback
    ) {
    private val colors: Array<Int> = arrayOf(
        ContextCompat.getColor(context, R.color.pink),
        ContextCompat.getColor(context, R.color.purple),
        ContextCompat.getColor(context, R.color.indigo),
        ContextCompat.getColor(context, R.color.blue),
        ContextCompat.getColor(context, R.color.green),
        ContextCompat.getColor(context, R.color.lime),
        ContextCompat.getColor(context, R.color.orange)
    )

    override fun createBinding(parent: ViewGroup): ForecastVH {
        return ForecastVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_forecast,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForecastVH, position: Int) {
        bind(holder, position)
    }

    private fun bind(viewHolder: ForecastVH, position: Int) {
        viewHolder.binding.forecast = getItem(position)

        viewHolder.binding.cardForecast.setCardBackgroundColor(colors[position])

        viewHolder.binding.executePendingBindings()
    }

    class ForecastVH(val binding: ItemForecastBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    class ForecastDiffCallback : DiffUtil.ItemCallback<Forecast.ForecastBase>() {
        override fun areItemsTheSame(
            oldItem: Forecast.ForecastBase,
            newItem: Forecast.ForecastBase
        ): Boolean {
            return TextUtils.equals(oldItem.date, newItem.date)
        }

        override fun areContentsTheSame(
            oldItem: Forecast.ForecastBase,
            newItem: Forecast.ForecastBase
        ): Boolean {
            return oldItem == newItem
        }
    }
}