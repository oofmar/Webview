package com.example.codingpractice

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID

class ProblemListViewModel: ViewModel() {
    val problems = mutableListOf<Problem>()
    init{
        for( i in 0 until 100){
            val problem = Problem(
                id = UUID.randomUUID(),
                title = "Problem #$i",
                date = Date(),
                isSolved = i%2==0
            )
            problems += problem
        }
    }
}