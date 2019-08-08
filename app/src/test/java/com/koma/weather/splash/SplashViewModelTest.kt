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
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.koma.weather.util.RxJavaRule
import com.koma.weather.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class SplashViewModelTest {
    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxJavaRule = RxJavaRule()

    private val application: Application = mock(Application::class.java)

    private lateinit var viewModel: SplashViewModel

    @Before
    fun setup() {
        viewModel = SplashViewModel(application)
    }

    @Test
    fun startCountDown() {
        val observer = mock<Observer<Long>>()
        viewModel.time.observeForever(observer)
        viewModel.startCountDown()
        verify(observer, times(1)).onChanged(5)
    }

    @Test
    fun getNeedSkip() {
        val observerNeedSkip = mock<Observer<Boolean>>()
        viewModel.needSkip.observeForever(observerNeedSkip)
        verify(observerNeedSkip).onChanged(false)

        val observerCountDown = mock<Observer<Long>>()
        viewModel.time.observeForever(observerCountDown)
        viewModel.startCountDown()
        verify(observerNeedSkip).onChanged(true)
    }

    @Test
    fun getTime() {
        val observer = mock<Observer<Long>>()
        viewModel.time.observeForever(observer)
        viewModel.startCountDown()
        verify(observer).onChanged(5)
        verify(observer).onChanged(4)
        verify(observer).onChanged(3)
        verify(observer).onChanged(2)
        verify(observer).onChanged(1)
    }
}