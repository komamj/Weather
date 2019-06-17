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

package com.koma.weather

import com.koma.common.base.BaseApplication
import com.koma.weather.di.AppComponent
import com.koma.weather.di.ApplicationModule
import com.koma.weather.di.DaggerAppComponent
import leakcanary.LeakSentry

class WeatherApplication : BaseApplication() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        LeakSentry.config = LeakSentry.config.copy(watchFragmentViews = false)
    }
}