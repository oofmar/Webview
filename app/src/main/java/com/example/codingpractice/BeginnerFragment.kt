package com.example.codingpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codingpractice.databinding.FragmentBeginnerBinding // Make sure this import matches your layout file's name

class BeginnerFragment : Fragment() {

    private var _binding: FragmentBeginnerBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBeginnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOpenProblem.setOnClickListener {
            // Create an action with the URL as an argument
            val action = BeginnerFragmentDirections.actionBeginnerFragmentToWebViewFragment("https://leetcode.com/problems/two-sum/")
            // Use the findNavController() method to navigate based on the action
            findNavController().navigate(action)
        }

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Set the binding to null to avoid memory leaks
        _binding = null
    }
}
