package com.example.maden

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.maden.databinding.FragmentRegisterBinding
import com.example.maden.model.SharedViewModel

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startLogin()

    }

    private fun startLogin(){
        binding.btLogin.setOnClickListener {
            sharedViewModel.checkLoginStatus(binding.etUsername.text.toString(),binding.etPass.text.toString())
        }
        sharedViewModel.loginStatus.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(R.id.action_registerFragment_to_startFragment)
            }else{
                Toast.makeText(requireContext(), "username or pass wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }


}