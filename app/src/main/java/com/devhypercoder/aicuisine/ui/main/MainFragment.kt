package com.devhypercoder.aicuisine.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.devhypercoder.aicuisine.R
import com.devhypercoder.aicuisine.ui.UserViewModel

class MainFragment : Fragment() {
  private val userViewModel: UserViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.main_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val text = "Hello ${userViewModel.userName.value}!"
    view.findViewById<TextView>(R.id.message).text = text
  }
}