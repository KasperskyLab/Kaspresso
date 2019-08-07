package com.kaspersky.kaspressample

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_button_next.setOnClickListener {
            with(supportFragmentManager) {
                val hasFragment = findFragmentByTag(SimpleFragment.TAG) != null

                if (hasFragment) {
                    Snackbar.make(
                        activity_main_frame_layout_root,
                        getString(R.string.add_fragment_error),
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    beginTransaction()
                        .add(R.id.activity_main_frame_layout_root, SimpleFragment.newInstance(), SimpleFragment.TAG)
                        .commitNow()
                }
            }
        }

        activity_main_button_webview.setOnClickListener {
            startActivity(
                Intent(this, WebViewActivity::class.java)
            )
        }
    }

}