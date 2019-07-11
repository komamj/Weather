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

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.koma.common.base.BaseFragment
import com.koma.weather.R
import com.koma.weather.databinding.FragmentSplashBinding
import com.koma.weather.di.Injectable
import com.koma.weather.main.MainActivity
import javax.inject.Inject

class SplashFragment : BaseFragment<FragmentSplashBinding>(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SplashViewModel

    override fun getLayoutId() = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SplashViewModel::class.java)

        binding.btnCountDown.setOnClickListener {
            showMainPage()
        }
        binding.time = viewModel.time
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(viewModel) {
            startCountDown()
            needSkip.observe(viewLifecycleOwner, Observer {
                if (it) {
                    showMainPage()
                }
            })
        }
    }

    private fun showMainPage() {
        view?.postDelayed({
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            activity?.run {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }, 500)
    }
}