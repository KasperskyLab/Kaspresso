package com.kaspersky.kaspresso.tutorial.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kaspersky.kaspresso.tutorial.databinding.FragmentLoadUserBinding
import kotlinx.coroutines.launch

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

class LoadUserFragment : Fragment() {

    private var _binding: FragmentLoadUserBinding? = null
    private val binding: FragmentLoadUserBinding
        get() = _binding ?: throw IllegalStateException("FragmentLoadUserBinding is null")

    private lateinit var viewModel: LoadUserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoadUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoadUserViewModel::class.java]
        binding.loadingButton.setOnClickListener {
            viewModel.loadUser()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        State.Initial -> {
                            binding.progressBarLoading.isVisible = false
                            binding.loadingButton.isEnabled = true
                            binding.error.isVisible = false
                            binding.username.isVisible = false
                        }
                        is State.Content -> {
                            binding.progressBarLoading.isVisible = false
                            binding.loadingButton.isEnabled = true
                            binding.error.isVisible = false
                            binding.username.isVisible = true

                            val user = state.user
                            binding.username.text = "${user.name} ${user.lastName}"
                        }
                        State.Error -> {
                            binding.progressBarLoading.isVisible = false
                            binding.loadingButton.isEnabled = true
                            binding.error.isVisible = true
                            binding.username.isVisible = false
                        }
                        State.Progress -> {
                            binding.progressBarLoading.isVisible = true
                            binding.loadingButton.isEnabled = false
                            binding.error.isVisible = false
                            binding.username.isVisible = false
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(): LoadUserFragment = LoadUserFragment()
    }
}
