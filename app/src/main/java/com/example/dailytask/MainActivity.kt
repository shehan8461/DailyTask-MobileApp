package com.example.dailytask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getstartbtn=findViewById<Button>(R.id.start_btn)

        getstartbtn.setOnClickListener {
            startActivity(Intent(this,TaskViews::class.java))
        }

    }
}