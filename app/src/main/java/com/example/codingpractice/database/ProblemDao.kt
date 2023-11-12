package com.example.codingpractice.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.codingpractice.Problem
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ProblemDao {
    @Query("SELECT * FROM problem")
    fun getProblems(): Flow<List<Problem>>

    @Query("SELECT * FROM problem WHERE id=(:id)")
    fun getProblem(id: UUID): Problem
    @Update
    suspend fun updateProblem(problem: Problem)
    @Insert
    suspend fun addProblem(problem: Problem)
}