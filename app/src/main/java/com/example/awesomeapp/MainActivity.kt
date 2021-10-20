package com.example.awesomeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.awesomeapp.ui.list.ListPhotoActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityScope.launch {
            delay(4000L)
            val moveListActivity = Intent(this@MainActivity, ListPhotoActivity::class.java)
            startActivity(moveListActivity)
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        activityScope.cancel()
    }
}