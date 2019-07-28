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
package com.koma.weather.splash

import android.app.Application
import androidx.lifecycle.LiveData
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.koma.weather.data.entities.City
import timber.log.Timber

class LocationLiveData(private val application: Application) : LiveData<City>() {
    private val aMapLocationClient: AMapLocationClient by lazy {
        AMapLocationClient(application).apply {
            setLocationListener {
                handleResult(it)
            }
        }
    }

    private val option: AMapLocationClientOption by lazy {
        AMapLocationClientOption().apply {
            locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
            isOnceLocationLatest = true
            interval = 3600000
            isNeedAddress = true
            isMockEnable = true
            httpTimeOut = 20000
            isLocationCacheEnable = false
        }
    }

    init {
        aMapLocationClient.setLocationOption(option)
    }

    private fun handleResult(aMapLocation: AMapLocation) {
        if (aMapLocation.errorCode == 0) {
            val latitude = aMapLocation.latitude
            val longitude = aMapLocation.longitude
            val cityName = aMapLocation.city
            val cityCode = aMapLocation.cityCode
            val city = City(cityCode, cityName)
            Timber.d("location successful latitude:$latitude,longitude:$longitude,cityName:$cityName,cityCode:$cityCode")
            value = city
        } else {
            Timber.e("location failed errorCode:${aMapLocation.errorCode},errorInfo:${aMapLocation.errorInfo}")
        }
    }

    override fun onActive() {
        super.onActive()

        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
        aMapLocationClient.stopLocation()
        aMapLocationClient.startLocation()
    }

    override fun onInactive() {
        super.onInactive()

        aMapLocationClient.stopLocation()
    }
}