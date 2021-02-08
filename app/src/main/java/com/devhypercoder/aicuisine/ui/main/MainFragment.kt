package com.devhypercoder.aicuisine.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.devhypercoder.aicuisine.R
import com.devhypercoder.aicuisine.databinding.MainFragmentBinding
import com.devhypercoder.aicuisine.ui.UserViewModel

class MainFragment : Fragment() {
  private val userViewModel: UserViewModel by activityViewModels()
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding: MainFragmentBinding =
      DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
    userViewModel.userName.observe(viewLifecycleOwner) {
      binding.userName = it
    }
    return binding.root
  }
}