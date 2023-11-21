package com.example.codingpractice

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL
import java.util.Date
import java.util.UUID

@Entity
data class Problem(
    @PrimaryKey val id: UUID,
    val title: String,
    val date: Date,
    val difficulty: String,
    val category: String,
    val url: String = "",
    val isSolved: Boolean,
    val sendTo: String=""
)