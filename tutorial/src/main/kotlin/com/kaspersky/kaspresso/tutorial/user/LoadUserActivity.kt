package com.kaspersky.kaspresso.tutorial.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.databinding.ActivityLoadUserBinding

class LoadUserActivity : AppCompatActivity() {

    private val binding: ActivityLoadUserBinding by lazy {
        ActivityLoadUserBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val isMvvmUsed = intent.getBooleanExtra(EXTRA_IS_MVVM_USED, false)
            val fragment = if (isMvvmUsed) {
                LoadUserMvvmFragment.newInstance()
            } else {
                LoadUserMvvmFragment.newInstance()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

    companion object {

        private const val EXTRA_IS_MVVM_USED = "is_mvvm_used"

        fun newIntentMvvm(context: Context): Intent = Intent(
            context,
            LoadUserActivity::class.java
        ).apply {
            putExtra(EXTRA_IS_MVVM_USED, true)
        }

        fun newIntentMvp(context: Context): Intent = Intent(
            context,
            LoadUserActivity::class.java
        ).apply {
            putExtra(EXTRA_IS_MVVM_USED, false)
        }
    }
}
