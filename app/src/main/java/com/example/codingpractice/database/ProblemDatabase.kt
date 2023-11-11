package com.example.codingpractice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.codingpractice.Problem

@Database(entities = [Problem::class], version=1)
@TypeConverters(ProblemTypeConverters::class)
abstract class ProblemDatabase: RoomDatabase(){
    abstract fun problemDao(): ProblemDao
}
