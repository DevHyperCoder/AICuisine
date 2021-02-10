package com.devhypercoder.aicuisine.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.devhypercoder.aicuisine.R
import com.devhypercoder.aicuisine.databinding.LoginFragmentBinding
import com.devhypercoder.aicuisine.ui.UserViewModel
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()

    companion object {
        const val TAG = "LoginFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: LoginFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        binding.loginButton.setOnClickListener {
            val auth = Firebase.auth
            val email = binding.loginEmailEditText.text.toString()
            val pass = binding.loginPasswordEditText.text.toString()
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (!it.isSuccessful) {
                    try {
                        throw it.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Log.e(TAG, "onCreateView: Username or Password is not correct!", e)
                    }
                    userViewModel.authStatus.value = false
                    return@addOnCompleteListener
                }
                userViewModel.authStatus.value = true
                userViewModel.email.value = email
                findNavController().popBackStack(R.id.mainFragment, false)
            }
        }

        binding.toRegisterTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }
}