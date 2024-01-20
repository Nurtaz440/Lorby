package com.example.lorby.ui.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lorby.R
import com.example.lorby.databinding.FragmentSignInBinding
import com.example.lorby.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewBinding by viewBinding(FragmentSignUpBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_verifyFragment)
        }
    }
}