package com.example.codingpractice

import java.util.Date
import java.util.UUID

data class Problem(
    val id: UUID,
    val title: String,
    val date: Date,
    val isSolved: Boolean
)