package com.example.shoppingapp_04.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp_04.R
import com.example.shoppingapp_04.data.model.User
import com.example.shoppingapp_04.data.util.Resource
import com.example.shoppingapp_04.databinding.FragmentEditProfileBinding
import com.example.shoppingapp_04.presentation.viewmodel.EditProfileViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private lateinit var binding : FragmentEditProfileBinding

    private lateinit var user : User

    @Inject
    lateinit var viewModel : EditProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_profile,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditProfileBinding.bind(view)
        user = EditProfileFragmentArgs.fromBundle(requireArguments()).user

        binding.editProfileFirstName.setText(user.name.firstname)
        binding.editProfileLastName.setText(user.name.lastname)
        binding.editProfileEmailAddress.setText(user.email)
        binding.editProfileUsername.setText(user.username)
        binding.editProfilePhoneNumber.setText(user.phone)
        binding.editProfileAddress.setText("${user.address.number}, ${user.address.street}, ${user.address.city}")

        binding.editProfileBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.editProfileButton.setOnClickListener {
            viewModel.updateUser(1,user.copy(id = 10, username = "${binding.editProfileUsername.editableText ?: "new username"}", phone = "0-11-2222-34")) //this is to test the update user api call
            viewModel.theUser.observe(viewLifecycleOwner){result ->
                when(result){
                    is Resource.Loading -> {
                        binding.editProfileProgress.visibility = View.VISIBLE
                        binding.editProfileButton.isEnabled = false
                        Log.i("EditProfileFragment","Loading...")
                    }
                    is Resource.Success -> {
                        binding.editProfileProgress.visibility = View.INVISIBLE
                        binding.editProfileButton.isEnabled = true
                        Log.i("EditProfileFragment","${result.data}")
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Updated Successfully!")
                            .setMessage("You've updated your profile successfully ):")
                            .setCancelable(false)
                            .setPositiveButton("Okay"){_,_,->
                                findNavController().navigateUp()
                            }.show()
                    }
                    is Resource.Error -> {
                        binding.editProfileProgress.visibility = View.INVISIBLE
                        binding.editProfileButton.isEnabled = true
                        Snackbar.make(binding.editProfileButton,"Error, ${result.message}",
                            Snackbar.LENGTH_SHORT).show()
                        Log.i("EditProfileFragment","Error, ${result.message}")
                    }
                }
            }
        }
    }
}