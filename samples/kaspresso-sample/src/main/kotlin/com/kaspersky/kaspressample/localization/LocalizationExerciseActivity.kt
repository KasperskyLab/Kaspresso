package com.kaspersky.kaspressample.localization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.databinding.ActivityLocalizationExerciseBinding

class LocalizationExerciseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLocalizationExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.localizationSaveButton.setOnClickListener {
            binding.localizationSavedMessage.visibility = android.view.View.VISIBLE
        }
        binding.localizationCancelButton.setOnClickListener {
            binding.localizationNameInput.text?.clear()
            binding.localizationEmailInput.text?.clear()
            binding.localizationSavedMessage.visibility = android.view.View.GONE
        }
    }
}
