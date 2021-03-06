package com.devhypercoder.aicuisine.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/***
 * @property email If authStatus = false, userName would be ""
 * @property authStatus
 * @author DevHyperCoder
 * UserViewModel => ViewModel to share the user across fragments.
 */
class UserViewModel : ViewModel() {
    val authStatus = MutableLiveData(false)
    val email = MutableLiveData("")
}