package com.example.random_art_generator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import android.widget.Toast
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_android)
        val myCanvas: ImageView = findViewById(R.id.my_canvas)
        myCanvas.setOnClickListener {
            canvasClicked()
        }
    }

    private fun canvasClicked() {
        Toast.makeText(this, "canvas clicked", Toast.LENGTH_SHORT).show()
    }
}
