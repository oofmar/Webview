package com.example.codingpractice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingpractice.databinding.FragmentProblemDetailBinding
import com.example.codingpractice.databinding.FragmentProblemListBinding

private const val TAG = "ProblemListFragment"
class ProblemListFragment : Fragment() {
    private var _binding: FragmentProblemListBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the view visible?"
        }
    private val problemListViewModel: ProblemListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${problemListViewModel.problems.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProblemListBinding.inflate(inflater, container, false)
        binding.problemRecyclerView.layoutManager = LinearLayoutManager(context)
        val problems = problemListViewModel.problems
        val adapter = ProblemListAdapter(problems)
        binding.problemRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}