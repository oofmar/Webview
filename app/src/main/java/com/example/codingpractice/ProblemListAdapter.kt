package com.example.codingpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codingpractice.databinding.ListItemProblemBinding
import java.util.UUID

class ProblemHolder(
    private val binding: ListItemProblemBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(problem: Problem, onProblemClicked: (problemId:UUID)-> Unit){
        binding.problemDate.text = problem.date.toString()
        binding.problemTitle.text = problem.title.toString()
        binding.root.setOnClickListener{
        onProblemClicked(problem.id)
        }
        binding.problemSolved.visibility = if (problem.isSolved){
            View.VISIBLE
        }
        else{
            View.GONE
        }
    }
}
class ProblemListAdapter(private val problems: List<Problem>, private val onProblemClicked: (problemId: UUID) -> Unit): RecyclerView.Adapter<ProblemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemProblemBinding.inflate(inflater, parent, false)
        return ProblemHolder(binding)
    }

    override fun onBindViewHolder(holder: ProblemHolder, position: Int) {
       val problem = problems[position]
      holder.bind(problem, onProblemClicked)
    }

    override fun getItemCount() = problems.size

}