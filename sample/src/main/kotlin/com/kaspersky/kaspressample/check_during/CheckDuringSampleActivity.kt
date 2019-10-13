package com.kaspersky.kaspressample.check_during

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlinx.android.synthetic.main.activity_check_during.*

class CheckDuringSampleActivity : AppCompatActivity() {

    private companion object {
        private val FAKE_MIN_DELAY = TimeUnit.SECONDS.toMillis(1)
        private val FAKE_MAX_DELAY = TimeUnit.SECONDS.toMillis(5)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_during)

        check_during_start_btn.setOnClickListener {
            check_during_start_btn.isEnabled = false

            Handler(mainLooper)
                .apply {
                    postDelayed(
                        {
                            // By some reason we can/want to show this dialog only until Android Oreo
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                                AlertDialog.Builder(this@CheckDuringSampleActivity).apply {
                                    setTitle(R.string.check_during_dialog_title)
                                    setPositiveButton(android.R.string.ok) { _, _ -> }
                                    show()
                                }
                            }
                            check_during_start_btn.isEnabled = true
                        },
                        // This timeout emulates real background work of application, like fetching from Internet, or computing some device taken data, we can't influence on time of which in E2E tests
                        Random.Default.nextLong(FAKE_MIN_DELAY, FAKE_MAX_DELAY)
                    )
                }
        }
    }
}