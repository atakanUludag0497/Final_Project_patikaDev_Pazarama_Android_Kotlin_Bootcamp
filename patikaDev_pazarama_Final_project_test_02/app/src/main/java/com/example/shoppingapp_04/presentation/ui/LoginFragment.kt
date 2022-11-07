package com.example.shoppingapp_04.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp_04.R
import com.example.shoppingapp_04.data.util.Utils.validateLoginRequest
import com.example.shoppingapp_04.databinding.FragmentLoginBinding
import com.example.shoppingapp_04.presentation.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModel: LoginViewModel
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        binding.loginUsername.setText("atakan456")
        binding.loginPassword.setText("thisIsApassword789")

        binding.loginButton.setOnClickListener {

            val username = binding.loginUsername.editableText.toString()
            val password = binding.loginPassword.editableText.toString()

            val result = validateLoginRequest(username, password)

            if (result.successful){
                binding.loginProgress.visibility = View.VISIBLE
                binding.loginButton.isEnabled = false

                viewModel.loginUser(username, password)

                viewModel.successful.observe(viewLifecycleOwner){successful ->
                    if (successful == true){
                        binding.loginProgress.visibility = View.INVISIBLE
                        binding.loginButton.isEnabled = true
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        viewModel.navigated()
                    }else if(successful == false){
                        binding.loginProgress.visibility = View.INVISIBLE
                        binding.loginButton.isEnabled = true
                        Snackbar.make(binding.loginButton,"${viewModel.error.value}", Snackbar.LENGTH_SHORT).show()
                        viewModel.navigated()
                    }
                }
            }
            else{
                Snackbar.make(binding.loginButton,"${result.error}", Snackbar.LENGTH_SHORT).show()
            }

        }


        binding.loginSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment2)
        }

    }
}