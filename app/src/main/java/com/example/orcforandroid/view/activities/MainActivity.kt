package com.example.orcforandroid.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.orcforandroid.R
import com.example.orcforandroid.databinding.ActivityMainBinding
import com.example.orcforandroid.view.fragments.LoginFragment
import com.example.orcforandroid.viewmodels.LOGIN_FRAGMENT_FLAG
import com.example.orcforandroid.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    val mainActViewModel by lazy{ ViewModelProviders.of(this).get(MainActivityViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main )
        binding.viewmodel = mainActViewModel
        //binding.lifecycleOwner = this

        mainActViewModel.fragmentFlag.observe(this, Observer { flag ->
            when(flag) {
                LOGIN_FRAGMENT_FLAG ->
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, LoginFragment.newInstance())
                        .commit()
            }
        })

    }
}
