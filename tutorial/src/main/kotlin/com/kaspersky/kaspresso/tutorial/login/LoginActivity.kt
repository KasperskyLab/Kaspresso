package com.kaspersky.kaspresso.tutorial.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val username = binding.inputUsername.text.trim()
        val password = binding.inputPassword.text.trim()
        if (username.length >= MIN_USERNAME_LENGTH && password.length >= MIN_PASSWORD_LENGTH) {
            launchNextScreen()
        }
    }

    private fun launchNextScreen() {
        startActivity(Intent(this, AfterLoginActivity::class.java))
    }

    companion object {

        private const val MIN_USERNAME_LENGTH = 3
        private const val MIN_PASSWORD_LENGTH = 6
    }
}
