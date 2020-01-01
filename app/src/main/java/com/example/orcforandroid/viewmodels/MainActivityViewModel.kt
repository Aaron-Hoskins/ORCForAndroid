package com.example.orcforandroid.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.orcforandroid.model.KEY_USER_SIGNED_IN
import com.example.orcforandroid.model.MAIN_SHARED_PREF_KEY

const val LOGIN_FRAGMENT_FLAG = 0
const val WELCOME_FRAGMENT = 1

class MainActivityViewModel(application : Application) : AndroidViewModel(application) {
    val fragmentFlag = MutableLiveData<Int>()
    private val sharedPref = application.applicationContext.getSharedPreferences(MAIN_SHARED_PREF_KEY, Context.MODE_PRIVATE)
init {
    if(sharedPref.getBoolean(KEY_USER_SIGNED_IN, false).not()) {
        fragmentFlag.postValue(LOGIN_FRAGMENT_FLAG)
    }
}

}