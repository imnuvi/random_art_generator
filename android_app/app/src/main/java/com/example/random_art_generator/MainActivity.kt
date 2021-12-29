package com.example.random_art_generator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_android)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice()

        }

    }

    private fun rollDice() {
        val rollValue: TextView = findViewById(R.id.roll_value)
//        val randval: Int = Random().nextInt(6) + 1
        val curval: String = rollValue.text.toString()
        val randval: Int = Integer.parseInt(curval) + 1
        rollValue.text = randval.toString()
    }
}