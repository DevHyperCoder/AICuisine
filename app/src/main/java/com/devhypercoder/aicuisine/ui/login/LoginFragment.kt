package com.devhypercoder.aicuisine.ui.login

import android.os.Bundle
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

class LoginFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: LoginFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        binding.loginButton.setOnClickListener {
            userViewModel.authStatus.value = !userViewModel.authStatus.value!!
            userViewModel.userName.value = "DevHyperCoder"
            findNavController().popBackStack(R.id.mainFragment, false)
        }

        binding.toRegisterTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }
}