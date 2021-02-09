package com.devhypercoder.aicuisine.ui.register

import android.os.Bundle
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

class RegisterFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: RegisterFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)

        binding.registerBtn.setOnClickListener {
            userViewModel.authStatus.value = !userViewModel.authStatus.value!!
            userViewModel.userName.value = "DevHyperCoder"
            findNavController().popBackStack(R.id.mainFragment, false)
        }

        binding.toLoginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }
}