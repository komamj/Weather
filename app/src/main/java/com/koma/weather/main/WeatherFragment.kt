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

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.koma.common.base.BaseFragment
import com.koma.weather.R
import com.koma.weather.data.entities.City
import com.koma.weather.databinding.FragmentWeatherBinding
import com.koma.weather.di.Injectable
import com.koma.weather.util.setAnimation
import com.koma.weather.util.setFactory
import com.koma.weather.util.setWeather
import timber.log.Timber
import javax.inject.Inject

class WeatherFragment : BaseFragment<FragmentWeatherBinding>(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WeatherViewModel

    private lateinit var city: City

    override fun getLayoutId() = R.layout.fragment_weather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.swipeRefreshLayout.apply {
            setColorSchemeColors(
                ContextCompat.getColor(context, R.color.colorPrimary),
                ContextCompat.getColor(context, R.color.colorPrimaryDark),
                ContextCompat.getColor(context, R.color.colorAccent)
            )
            setOnRefreshListener {
                refreshWeather()
            }
            isRefreshing = false
        }
        with(binding.now) {
            tsTmp.setFactory(R.style.TmpStyle)
            tsTmp.setAnimation(R.anim.slide_in_right, R.anim.slide_in_right)
            tsDescription.setFactory(R.style.DescriptionStyle)
            tsDescription.setAnimation(R.anim.slide_in_right, R.anim.slide_in_right)
            tsHumDescription.setFactory(R.style.HumStyle)
            tsHumDescription.setAnimation(R.anim.slide_in_bottom, R.anim.slide_out_top)
            tsWindDescription.setFactory(R.style.WindSpeedStyle)
            tsWindDescription.setAnimation(R.anim.slide_in_bottom, R.anim.slide_out_top)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(WeatherViewModel::class.java)

        arguments?.run {
            city = getParcelable(KEY_CITY)

            Timber.d("current city is $city")
        }

        refreshWeather()

        startObserve()
    }

    private fun refreshWeather() {
        viewModel.getWeatherNow(city)
    }

    private fun startObserve() {
        binding.weatherNow = viewModel.now

        viewModel.now.observe(viewLifecycleOwner, Observer {
            binding.now.tsTmp.setText(getString(R.string.temperature, it.tmp))
            binding.now.tsDescription.setText(it.condTxt)
            binding.now.tsHumDescription.setText("${it.hum}%")
            binding.now.tsWindDescription.setText(
                getString(R.string.wind_description, it.windSpd)
            )
            binding.now.animationView.setWeather(it)
            binding.now.animationView.playAnimation()
        })
    }

    companion object {
        private const val KEY_CITY = "key_city"

        fun newInstance(city: City): WeatherFragment {
            val fragment = WeatherFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY_CITY, city)
            fragment.arguments = bundle
            return fragment
        }
    }
}