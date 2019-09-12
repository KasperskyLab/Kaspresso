package com.kaspersky.kaspressample.docloc

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.fragment_screenshot.*

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