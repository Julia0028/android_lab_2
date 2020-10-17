package com.example.lab_2

class Seconds {
    var secondsElapsed: Int = 0
    var startTimer: Boolean = true


    fun addSeconds(): Int {
        return secondsElapsed++
    }

    fun startSeconds() {
        startTimer = true
    }


    fun stopSeconds() {
        startTimer = false
    }

    fun getSeconds(): Int {
        return secondsElapsed
    }

    fun putSeconds(newTimer: Int) {
        secondsElapsed = newTimer
    }

    fun isStartTimer(): Boolean {
        return startTimer
    }

}