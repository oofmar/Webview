package com.example.codingpractice

import android.content.Context
import androidx.room.Room
import com.example.codingpractice.database.ProblemDatabase
import com.example.codingpractice.database.migration_1_2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

private const val DATABASE_NAME = "problem-database"
class ProblemRepository private constructor(context: Context, private val coroutineScope: CoroutineScope= GlobalScope) {
    private val database: ProblemDatabase=
        Room.databaseBuilder(context.applicationContext, ProblemDatabase::class.java, DATABASE_NAME).
//    createFromAsset(DATABASE_NAME).
            allowMainThreadQueries().addMigrations(migration_1_2).build()
    suspend fun getProblems(): Flow<List<Problem>> = database.problemDao().getProblems()
    suspend fun getProblem(id:UUID): Problem = database.problemDao().getProblem(id)

   fun updateProblem(problem: Problem){
        coroutineScope.launch{
            database.problemDao().updateProblem(problem)
        }
    }
    suspend fun addProblem(problem:Problem){
        database.problemDao().addProblem(problem)
    }

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