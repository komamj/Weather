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

package com.koma.weather.util

import android.os.Build
import android.view.Gravity
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.annotation.AnimRes
import androidx.annotation.StyleRes

fun TextSwitcher.setFactory(@StyleRes styleId: Int) {
    this.setFactory {
        val textView = TextView(context)
            .apply {
                gravity = Gravity.CENTER
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    setTextAppearance(context, styleId)
                } else {
                    setTextAppearance(styleId)
                }
            }
        textView
    }
}

fun TextSwitcher.setAnimation(@AnimRes inAnimResId: Int, @AnimRes outAnimResId: Int) {
    setInAnimation(context, inAnimResId)
    setOutAnimation(context, outAnimResId)
}