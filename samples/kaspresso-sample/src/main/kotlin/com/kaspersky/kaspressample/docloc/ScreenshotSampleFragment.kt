package com.kaspersky.kaspressample.docloc

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.databinding.FragmentScreenshotBinding

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
