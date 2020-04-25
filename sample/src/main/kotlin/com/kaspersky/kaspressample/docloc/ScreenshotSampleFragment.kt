package com.kaspersky.kaspressample.docloc

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.fragment_screenshot.background
import kotlinx.android.synthetic.main.fragment_screenshot.counter
import kotlinx.android.synthetic.main.fragment_screenshot.decrement
import kotlinx.android.synthetic.main.fragment_screenshot.increment
import kotlinx.android.synthetic.main.fragment_screenshot.set_black_background
import kotlinx.android.synthetic.main.fragment_screenshot.set_red_background

class ScreenshotSampleFragment : Fragment(), ScreenshotSampleView {

    private val presenter = ScreenshotSamplePresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_screenshot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()

        increment.setOnClickListener { presenter.increment() }
        decrement.setOnClickListener { presenter.decrement() }
        set_black_background.setOnClickListener { presenter.setBackgroundColor(Color.BLACK) }
        set_red_background.setOnClickListener { presenter.setBackgroundColor(Color.RED) }
    }

    override fun setCounterValue(value: Int) {
        counter.text = getString(R.string.counter_value, value)
    }

    override fun setBackgroundColor(color: Int) {
        background.setBackgroundColor(color)
    }
}
