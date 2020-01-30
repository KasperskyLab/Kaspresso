package com.kaspersky.kaspressample.idlingwait

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_idlewaiting.*

class WaitForIdleActivity : AppCompatActivity() {

    companion object {
        private const val INIT_DELAY: Long = 2_000
        private const val PERIOD: Long = 100
        private const val TOTAL_TIME: Long = 20_000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idlewaiting)
    }

    override fun onResume() {
        super.onResume()
        val scheduler = Executors.newScheduledThreadPool(1)
        val periodicTask = scheduler.scheduleAtFixedRate(
            { Handler(Looper.getMainLooper()).post { edit.setText(R.string.idlewaiting_fragment_text_edittext) } },
            INIT_DELAY,
            PERIOD,
            TimeUnit.MILLISECONDS
        )
        scheduler.schedule({ periodicTask.cancel(true) }, TOTAL_TIME, TimeUnit.MILLISECONDS)
    }
}