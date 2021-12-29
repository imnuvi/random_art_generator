package com.example.random_art_generator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_android)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollButton.text = "let's roll";
            Toast.makeText(this,  "clicked bro", Toast.LENGTH_SHORT).show()
        }

    }
}