package com.example.random_art_generator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import android.widget.Toast
import android.widget.ImageView
import android.view.ViewGroup.LayoutParams
import android.view.View.OnClickListener
import com.example.random_art_generator.MyCanvasView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_android)
        val myCanvas: MyCanvasView = findViewById(R.id.my_canvas)
//        myCanvas.setLayoutParams(LayoutParams(100, 200))
        myCanvas.setOnClickListener (){
            myCanvas.invalidate()
            canvasClicked()
        }
    }

    private fun canvasClicked() {
//        Toast.makeText(this, "canvas clicked", Toast.LENGTH_SHORT).show()
    }
}
