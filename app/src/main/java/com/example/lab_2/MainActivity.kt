package com.example.lab_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var timer: Seconds

    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            if (timer.isStartTimer())
                textSecondsElapsed.post {
                    textSecondsElapsed.setText("Seconds elapsed: " + timer.addSeconds())
                }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timer = Seconds()
        if (savedInstanceState != null)
            timer.putSeconds(savedInstanceState.getInt("seconds_key"))
        Log.i("MainActivity", "onCreate called")
        setContentView(R.layout.activity_main)
        backgroundThread.start()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onReStart called")
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart called")
    }


    override fun onResume() {
        super.onResume()
        timer.startSeconds()
        Log.i("MainActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        timer.stopSeconds()
        Log.i("MainActivity", "onPause called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds_key", timer.getSeconds())
        Log.i("MainActivity", "onSaveInstanceState called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy called")
    }
}