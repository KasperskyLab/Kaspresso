package com.kaspersky.kaspressample.systemdialogs

import android.Manifest
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.kaspersky.kaspressample.databinding.ActivitySystemDialogsBinding

class SystemDialogsActivity : AppCompatActivity() {

    companion object {
        private const val RESULT_CODE = 10
    }

    private lateinit var binding: ActivitySystemDialogsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        binding = ActivitySystemDialogsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.button1.setOnClickListener {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_CALL_LOG),
                RESULT_CODE)
        }
    }
}
