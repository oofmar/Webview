package com.example.codingpractice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingpractice.databinding.FragmentProblemDetailBinding
import com.example.codingpractice.databinding.FragmentProblemListBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.net.URL
import java.util.Date
import java.util.UUID


class ProblemListFragment : Fragment() {
    private var _binding: FragmentProblemListBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the view visible?"
        }
    private val problemListViewModel: ProblemListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProblemListBinding.inflate(inflater, container, false)
        binding.problemRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                problemListViewModel.problems.collect{ problems ->
                    binding.problemRecyclerView.adapter = ProblemListAdapter(problems){problemId->
                        findNavController().navigate(ProblemListFragmentDirections.showProblemDetail(problemId))
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_problem_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.new_problem->{
                showNewProblem()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun showNewProblem(){
        viewLifecycleOwner.lifecycleScope.launch{
            val newProblem = Problem(
                id = UUID.randomUUID(),
                title ="",
                date = Date(),
                isSolved = false
            )
            problemListViewModel.addProblem(newProblem)
            findNavController().navigate(ProblemListFragmentDirections.showProblemDetail(newProblem.id))
        }
    }
}