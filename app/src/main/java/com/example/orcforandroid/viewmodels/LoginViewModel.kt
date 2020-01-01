package com.example.orcforandroid.viewmodels

import android.content.Intent
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orcforandroid.BuildConfig
import com.example.orcforandroid.R
import com.example.orcforandroid.model.*
import com.example.orcforandroid.model.datasources.authentication.FirebaseAuthenticationHandler
import com.example.orcforandroid.utils.ValidationUtil
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.view.*

class LoginViewModel : ViewModel(), FirebaseAuthenticationHandler.AuthenticationCallbacks {


    val userEmail = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val emailValidation = MutableLiveData<String>()
    val passwordValidation = MutableLiveData<String>()
    val nextAction = MutableLiveData<Int>()

    fun afterPasswordEdit(editable : Editable) {
        password.postValue(editable.toString())
        if(ValidationUtil.isValidPassword(editable.toString())) {
            passwordValidation.postValue(PASSWORD_OK_FORMAT)
        } else {
            passwordValidation.postValue(PASSWORD_INCORRECT_LENGTH)
        }
    }

    fun afterEmailEdit(editable : Editable) {
        userEmail.postValue(editable.toString())
        if(ValidationUtil.isEmailAddressValid(editable.toString())) {
            emailValidation.postValue(EMAIL_VALID)
        } else {
            emailValidation.postValue(EMAIL_INVALID)
        }
    }

    fun onViewClicked(view : View) {
        when(view.id) {
            R.id.btnCreateAccount ->{}
            R.id.btnSignIn -> {initiateSignIn(UserLogin(userEmail.value?: "", password.value?: ""))}
        }
    }

    fun initiateSignIn(userLogin: UserLogin) {
        if(BuildConfig.BUILD_TYPE.equals("debug")) {
            logUserInput()
        }
        FirebaseAuthenticationHandler.signIntoAuthenticationAccount(userLogin, this)
    }

    fun logUserInput() {
        val noInput = "NOTHING ENTERED"
        val currentInfo = "User Email: ${userEmail.value?: noInput} |||  Password: ${password.value?: noInput}"
        Log.d("TAG_LOGIN_VM", currentInfo)
    }

    override fun onCreateAccount(accountCreated: Boolean, exception: Exception?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSignIntoAccount(signedIn: Boolean, exception: Exception?) {
        if(signedIn) {
            Log.d("TAG_LOGIN_VM", "${FirebaseAuth.getInstance().currentUser?.email} signed In")
            nextAction.postValue(OPEN_HOME_ACTIVITY)
        } else {
            Log.e("TAG_LOGIN_VM", "Signing into app failed", exception)
        }
    }
}
