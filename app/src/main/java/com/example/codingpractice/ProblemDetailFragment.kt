package com.example.codingpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.codingpractice.databinding.FragmentProblemDetailBinding
import java.util.Date
import java.util.UUID

class ProblemDetailFragment: Fragment() {
    private var _binding: FragmentProblemDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is this view visible?"
        }

    private lateinit var problem: Problem
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        problem = Problem(
            id = UUID.randomUUID(),
            title = "",
            date = Date(),
            isSolved = false
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProblemDetailBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            problemTitle.doOnTextChanged { text, _, _,_ ->
                problem = problem.copy(title = text.toString())
            }
            problemDate.apply{
                text = problem.date.toString()
                isEnabled = false
            }
            problemSolved.setOnCheckedChangeListener(){_, isChecked->
                problem = problem.copy(isSolved = isChecked)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}