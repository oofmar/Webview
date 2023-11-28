package com.example.codingpractice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codingpractice.databinding.FragmentBeginnerBinding
import com.example.codingpractice.databinding.FragmentIntermediateBinding

class IntermediateFragment : Fragment(R.layout.fragment_intermediate) {
    private var binding: FragmentIntermediateBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentBinding = FragmentIntermediateBinding.bind(view)
        binding = fragmentBinding

        binding?.buttonBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}