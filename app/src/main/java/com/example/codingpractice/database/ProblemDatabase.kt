package com.example.codingpractice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.codingpractice.Problem

@Database(entities = [Problem::class], version=1)
@TypeConverters(ProblemTypeConverters::class)
abstract class ProblemDatabase: RoomDatabase(){
    abstract fun problemDao(): ProblemDao
}
val migration_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase){
        database.execSQL("ALTER TABLE Problem ADD COLUMN user TEXT NOT NULL DEFAULT ' '")
    }
}