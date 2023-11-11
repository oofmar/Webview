package com.example.codingpractice

import android.content.Context
import androidx.room.Room
import com.example.codingpractice.database.ProblemDatabase
import kotlinx.coroutines.flow.Flow
import java.util.UUID

private const val DATABASE_NAME = "problem-database"
class ProblemRepository private constructor(context: Context) {
    private val database: ProblemDatabase=Room.databaseBuilder(context.applicationContext, ProblemDatabase::class.java, DATABASE_NAME).createFromAsset(
        DATABASE_NAME).build()
    suspend fun getProblems(): Flow<List<Problem>> = database.problemDao().getProblems()
    suspend fun getProblem(id:UUID): Problem = database.problemDao().getProblem(id)

    companion object{
        private var INSTANCE: ProblemRepository? = null
        fun initalize(context: Context){
            if(INSTANCE==null){
                INSTANCE = ProblemRepository(context)
            }
        }
        fun get(): ProblemRepository{
            return INSTANCE?:
            throw IllegalStateException("ProblemRepository must be initialized")
        }
    }
}