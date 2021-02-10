package com.devhypercoder.aicuisine.ui.register

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
import com.devhypercoder.aicuisine.databinding.RegisterFragmentBinding
import com.devhypercoder.aicuisine.ui.UserViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {
    companion object {
        const val TAG = "RegisterFragment"
    }

    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: RegisterFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)

        binding.registerBtn.setOnClickListener {
            val auth = Firebase.auth
            val email = binding.registerEmailEditText.text.toString()
            val pass = binding.registerPasswordEditText.text.toString()

            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.e(TAG, "onCreateView: createUserWithEmailAndPassword", it.exception)
                    userViewModel.authStatus.value = false
                    return@addOnCompleteListener
                }

                userViewModel.authStatus.value = true
                userViewModel.email.value = email
                findNavController().popBackStack(R.id.mainFragment, false)
            }
        }

        binding.toLoginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }
}