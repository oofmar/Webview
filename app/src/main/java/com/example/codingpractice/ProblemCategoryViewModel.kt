package com.example.codingpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProblemCategoryViewModel: ViewModel() {
//    private val problemListRepository = ProblemListRepository.get()
    private val _problemList: MutableStateFlow<MutableStateFlow<List<Problem>>> = MutableStateFlow(
        MutableStateFlow(emptyList())
    )
    private val problemList: StateFlow<StateFlow<List<Problem>>>
        get()= _problemList.asStateFlow()
    init{
        viewModelScope.launch{
        }
    }
}