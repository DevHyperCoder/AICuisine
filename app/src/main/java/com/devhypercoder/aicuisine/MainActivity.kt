package com.devhypercoder.aicuisine

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.devhypercoder.aicuisine.ui.UserViewModel

class MainActivity : AppCompatActivity() {
  private val userViewModel: UserViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController

    userViewModel.authStatus.observe(this) { authStatus ->
      if (authStatus) {
        navController.navigate(R.id.mainFragment)
        return@observe
      }
      navController.navigate(R.id.loginFragment)
    }
  }
}