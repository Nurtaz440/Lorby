package com.example.lorby.ui.signUp

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lorby.R
import com.example.lorby.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewBinding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: RegistrationViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        viewBinding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validatePassword(s.toString(),"")
            }
        })
        viewBinding.etAgainPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validatePassword(viewBinding.etPassword.text.toString(), s.toString())
            }
        })


    }


    private fun validatePassword(password: String,confirmPassword: String) {
        val containsSymbol = containsSymbol(password)
        val containsNumber = containsNumber(password)
        val containsLowercase = containsLowercase(password)
        val containsUppercase = containsUppercase(password)
        val isLengthValid = password.length in 8..15

        val isPasswordConfirmed = password == confirmPassword

        // Update confirmation status
        if (isPasswordConfirmed) {
            viewBinding.tvPasswordError.visibility = View.GONE
        } else {
            viewBinding.tvPasswordError.visibility = View.VISIBLE
        }

        if (containsSymbol) {
            viewBinding.tvSymbol.setTextColor(Color.GREEN)
            viewBinding.ivSymbol.setImageResource(R.drawable.baseline_check_box_24)
            viewBinding.ivSymbol.rotation = 0f
            viewBinding.ivSymbol.visibility = View.VISIBLE
        } else {
            viewBinding.tvSymbol.setTextColor(Color.RED)
            viewBinding.ivSymbol.setImageResource(R.drawable.baseline_add_24)
            viewBinding.ivSymbol.rotation = 45f
            viewBinding.ivSymbol.visibility = View.VISIBLE
        }
        if (containsNumber) {
            viewBinding.tvNumber.setTextColor(Color.GREEN)
            viewBinding.ivNumber.setImageResource(R.drawable.baseline_check_box_24)
            viewBinding.ivNumber.rotation = 0f
            viewBinding.ivNumber.visibility = View.VISIBLE
        } else {
            viewBinding.tvNumber.setTextColor(Color.RED)
            viewBinding.ivNumber.setImageResource(R.drawable.baseline_add_24)
            viewBinding.ivNumber.rotation = 45f
            viewBinding.ivNumber.visibility = View.VISIBLE
        }

        if (isLengthValid) {
            viewBinding.tvLength.setTextColor(Color.GREEN)
            viewBinding.ivLength.setImageResource(R.drawable.baseline_check_box_24)
            viewBinding.ivLength.rotation = 0f
            viewBinding.ivLength.visibility = View.VISIBLE
        } else {
            viewBinding.tvLength.setTextColor(Color.RED)
            viewBinding.ivLength.setImageResource(R.drawable.baseline_add_24)
            viewBinding.ivLength.rotation = 45f
            viewBinding.ivLength.visibility = View.VISIBLE
        }

        if (containsLowercase && containsUppercase) {
            viewBinding.tvCapitalLetter.setTextColor(Color.GREEN)
            viewBinding.ivCapitalLetter.setImageResource(R.drawable.baseline_check_box_24)
            viewBinding.ivCapitalLetter.rotation = 0f
            viewBinding.ivCapitalLetter.visibility = View.VISIBLE
        } else {
            viewBinding.tvCapitalLetter.setTextColor(Color.RED)
            viewBinding.ivCapitalLetter.setImageResource(R.drawable.baseline_add_24)
            viewBinding.ivCapitalLetter.rotation = 45f
            viewBinding.ivCapitalLetter.visibility = View.VISIBLE
        }



        // Enable/disable button based on field statuses
        val allFieldsFilled = containsSymbol && containsNumber &&
                containsLowercase && containsUppercase && isLengthValid && viewBinding.etEmail.text.toString().trim().isNotEmpty() &&
                viewBinding.etLogin.text.toString().trim().isNotEmpty() && isPasswordConfirmed
        Log.d("TAG", "validatePassword: $allFieldsFilled")
        Log.d("TAG", "validatePassword: $isPasswordConfirmed")
        viewBinding.btnContinue.apply {
            isEnabled = allFieldsFilled
            isClickable = allFieldsFilled
            if (allFieldsFilled) {
                setBackgroundColor(resources.getColor(R.color.button_dark))
                setTextColor(resources.getColor(R.color.white))
                viewBinding.btnContinue.setOnClickListener {
                    viewModel.regstration(
                        viewBinding.etEmail.text.toString(),
                        viewBinding.etLogin.text.toString(),
                        viewBinding.etPassword.text.toString(),
                        viewBinding.etAgainPassword.text.toString()
                    )
                    viewModel.loading.observe(viewLifecycleOwner){
                        if (it){
                            viewBinding.progressBar.visibility=View.VISIBLE

                        }else{
                            viewBinding.progressBar.visibility=View.GONE
                        }
                    }
                }
            }
            else{
                setBackgroundColor(resources.getColor(R.color.button_light))
                setTextColor(resources.getColor(R.color.text_grey))
            }
        }
    }

    private fun containsSymbol(password: String): Boolean {
        val symbols = setOf('!', '@', '#', '$', '%', '^', '&', '*')
        return password.any { symbols.contains(it) }
    }

    private fun containsNumber(password: String): Boolean {
        return password.any { it.isDigit() }
    }

    private fun containsLowercase(password: String): Boolean {
        return password.any { it.isLowerCase() }
    }

    private fun containsUppercase(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }
}