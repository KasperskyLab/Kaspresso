package com.kaspersky.kaspressample.docloc

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.databinding.FragmentScreenshotBinding

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

class ScreenshotSampleFragment : Fragment(), ScreenshotSampleView {

    private val presenter = ScreenshotSamplePresenter(this)
    private val binding get() = requireNotNull(_binding)

    private var _binding: FragmentScreenshotBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentScreenshotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()

        binding.increment.setOnClickListener { presenter.increment() }
        binding.decrement.setOnClickListener { presenter.decrement() }
        binding.setBlackBackground.setOnClickListener { presenter.setBackgroundColor(Color.BLACK) }
        binding.setRedBackground.setOnClickListener { presenter.setBackgroundColor(Color.RED) }
    }

    override fun setCounterValue(value: Int) {
        binding.counter.text = getString(R.string.counter_value, value)
    }

    override fun setBackgroundColor(color: Int) {
        binding.background.setBackgroundColor(color)
    }
}
