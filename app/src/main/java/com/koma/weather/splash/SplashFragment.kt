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
import com.koma.common.base.BaseFragment
import com.koma.weather.MainActivity
import com.koma.weather.R
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : BaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        btn_count_down.apply {
            this.text = getString(R.string.count_down, 3)
            this.setOnClickListener {
                showMainPage()
            }
        }
    }

    private fun showMainPage() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity?.run {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    override fun getLayoutId() = R.layout.fragment_splash
}