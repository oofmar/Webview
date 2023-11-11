package com.example.codingpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.codingpractice.databinding.ListItemProblemBinding

class ProblemHolder(
    private val binding: ListItemProblemBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(problem: Problem){
        binding.problemDate.text = problem.date.toString()
        binding.problemTitle.text = problem.title.toString()
        binding.root.setOnClickListener{
            Toast.makeText(binding.root.context, "${problem.title} clicked ", Toast.LENGTH_SHORT).show()
        }
        binding.problemSolved.visibility = if (problem.isSolved){
            View.VISIBLE
        }
        else{
            View.GONE
        }
    }
}
class ProblemListAdapter(private val problems: List<Problem>): RecyclerView.Adapter<ProblemHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemProblemBinding.inflate(inflater, parent, false)
        return ProblemHolder(binding)
    }

    override fun onBindViewHolder(holder: ProblemHolder, position: Int) {
       val problem = problems[position]
      holder.bind(problem)
    }

    override fun getItemCount() = problems.size

}