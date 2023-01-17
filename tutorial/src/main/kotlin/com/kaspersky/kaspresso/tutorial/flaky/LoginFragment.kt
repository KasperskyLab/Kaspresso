package com.kaspersky.kaspresso.tutorial.flaky

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaspersky.kaspresso.tutorial.afterlogin.AfterLoginActivity
import com.kaspersky.kaspresso.tutorial.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("FragmentLoginBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        context?.let {
            startActivity(
                Intent(it, AfterLoginActivity::class.java)
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val MIN_USERNAME_LENGTH = 3
        private const val MIN_PASSWORD_LENGTH = 6
    }
}
