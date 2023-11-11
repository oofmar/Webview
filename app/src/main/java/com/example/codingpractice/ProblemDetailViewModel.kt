package com.example.codingpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class ProblemDetailViewModel(problemId: UUID): ViewModel() {
    private val problemRepository = ProblemRepository.get()
    private val _problem: MutableStateFlow<Problem?> = MutableStateFlow(null)
    val problem: StateFlow<Problem?> = _problem.asStateFlow()
    init{
        viewModelScope.launch{
            _problem.value = problemRepository.getProblem(problemId)
        }
    }
    fun updateProblem(onUpdate: (Problem)->Problem){
        _problem.update{ oldProblem->
            oldProblem?.let{onUpdate(it)}
        }
    }
    override fun onCleared(){
        super.onCleared()
            problem.value?.let{
                problemRepository.updateProblem(it)
        }
    }
}

class ProblemDetailViewModelFactory(
    private val problemId: UUID
):  ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProblemDetailViewModel(problemId) as T
    }
}