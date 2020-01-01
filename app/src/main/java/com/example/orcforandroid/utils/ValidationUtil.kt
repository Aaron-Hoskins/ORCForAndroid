package com.example.orcforandroid.utils

import android.util.Patterns

class ValidationUtil {
    companion object{
        fun isEmailAddressValid(emailAddress : String) : Boolean {
            if(emailAddress.isBlank()) {
                return false
            }

            return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
        }

        fun isValidPassword(password : String) : Boolean {
            if(password.isBlank()) {
                return false
            }
            return password.length > 6
        }
    }
}