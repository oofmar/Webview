package com.example.codingpractice

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import kotlinx.coroutines.flow.collect

private const val TAG = "CrimeListViewModel"
class ProblemListViewModel: ViewModel() {
    private val problemRepository = ProblemRepository.get()
    private val _problems: MutableStateFlow<List<Problem>> = MutableStateFlow(emptyList())


    val problems: StateFlow<List<Problem>>
        get() = _problems.asStateFlow()
    init{

        viewModelScope.launch {
            problemRepository.getProblems().collect{
                _problems.value = it
            }
        }
    }
    suspend fun addProblem(problem: Problem){
        problemRepository.addProblem(problem)
    }
}