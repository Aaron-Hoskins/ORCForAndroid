package com.example.orcforandroid.model.datasources.authentication

import com.example.orcforandroid.model.UserLogin
import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthenticationHandler {
    private val authentication  = FirebaseAuth.getInstance()
    val currentUser by lazy { authentication.currentUser }

    fun createAuthenticationAccount(userLogin: UserLogin , callback : AuthenticationCallbacks) {
        authentication
            .createUserWithEmailAndPassword(userLogin.email, userLogin.password)
            .addOnCompleteListener{ task ->
                callback.onCreateAccount(task.isSuccessful, task.exception)
            }
    }

    fun signIntoAuthenticationAccount(userLogin: UserLogin , callback : AuthenticationCallbacks){
        authentication
            .signInWithEmailAndPassword(userLogin.email, userLogin.password)
            .addOnCompleteListener{ task ->
                callback.onSignIntoAccount(task.isSuccessful, task.exception)
            }
    }

    fun signOutOfTheAccount() = authentication.signOut()

   interface AuthenticationCallbacks{
       fun onCreateAccount(accountCreated : Boolean, exception : Exception?)
       fun onSignIntoAccount(signedIn : Boolean, exception : Exception?)
   }
}