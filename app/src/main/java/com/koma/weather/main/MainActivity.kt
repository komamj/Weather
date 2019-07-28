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
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.koma.common.base.BaseActivity
import com.koma.weather.R
import com.koma.weather.data.entities.City
import com.koma.weather.databinding.ActivityMainBinding
import com.koma.weather.util.Constants
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val cities = mutableListOf<City>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews(binding)
    }

    private fun initViews(binding: ActivityMainBinding) {
        binding.bottomAppBar.title = "wocao"
        binding.bottomAppBar.subtitle = "wocao1111"
        setSupportActionBar(binding.bottomAppBar)

        cities.add(City("0000", intent.getStringExtra(Constants.KEY_LOCATION)))

        with(binding.viewPager) {
            currentItem = 0
            offscreenPageLimit = 1
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = WeatherAdapter(this@MainActivity, cities)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun getLayoutId() = R.layout.activity_main
}
