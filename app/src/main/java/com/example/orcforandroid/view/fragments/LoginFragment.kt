package com.example.orcforandroid.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.example.orcforandroid.R
import com.example.orcforandroid.databinding.LoginFragmentBinding
import com.example.orcforandroid.model.OPEN_HOME_ACTIVITY
import com.example.orcforandroid.view.activities.HomeActivity
import com.example.orcforandroid.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.loginviewmodel = viewModel

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.emailValidation.observe(viewLifecycleOwner, Observer { msg ->  tvLoginUserEmailMsg.text = msg})
        viewModel.passwordValidation.observe(viewLifecycleOwner, Observer { msg ->  tvLoginPasswordMsg.text = msg})
        viewModel.nextAction.observe(viewLifecycleOwner, Observer { flag ->
            when(flag) {
                OPEN_HOME_ACTIVITY -> {
                    startActivity(Intent(context, HomeActivity::class.java))
                    activity?.finish()
                }
            }
        })
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
