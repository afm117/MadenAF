package com.example.maden

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.maden.adapter.TimerSaveAdapter
import com.example.maden.databinding.FragmentStartBinding
import com.example.maden.model.SharedViewModel

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRc()
        timerListener()
       binding.btStart.setOnClickListener {
           if (!sharedViewModel.timerStatus){
               startTimer()
               binding.btStart.text = "Next"
           }else{
               val second = (sharedViewModel.lastSecond.value?:0).toString()
                val minus = (sharedViewModel.lastMinus.value?:0).toString()
               (binding.rvTime.adapter as TimerSaveAdapter).addItem(second, minus)
           }

       }
        binding.btStop.setOnClickListener {
            binding.btStart.text = "Start"
            stopTimer()
        }
    }

    private fun setupRc(){
        binding.rvTime.adapter = TimerSaveAdapter()
    }

    private fun timerListener(){
        sharedViewModel.lastSecond.observe(viewLifecycleOwner){
            binding.tvSecond.text = it.toString()
        }
        sharedViewModel.lastMinus.observe(viewLifecycleOwner){
            binding.tvMinus.text = it.toString()
        }

    }

    private fun startTimer(){
        sharedViewModel.startTimer()
    }

    private fun stopTimer(){
        sharedViewModel.timerStatus = false
    }

}