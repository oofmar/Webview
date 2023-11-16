package com.example.codingpractice

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

private const val DATABASE_NAME = "problem-category-database"
class ProblemListRepository private constructor(context: Context, private val coroutineScope: CoroutineScope = GlobalScope ) {

}