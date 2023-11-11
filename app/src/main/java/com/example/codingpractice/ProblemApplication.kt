package com.example.codingpractice

import android.app.Application

class ProblemApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ProblemRepository.initalize(this)
    }
}