package com.example.random_art_generator

import android.widget.ImageView
import android.graphics.Canvas
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat


class MyCanvasView @JvmOverloads constructor(context: Context, attrs: AttributeSet?= null, defStyleAttr: Int=0) : ImageView(context, attrs, defStyleAttr){
    private lateinit var my_canvas : Canvas

    private var drawcolor = ResourcesCompat.getColor(resources, R.color.white, null)
    private var my_paint = Paint().apply {
        color = drawcolor
        style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // todo rerender the data with the updated sizes
        super.onSizeChanged(w, h, oldw, oldh)
    }

    // this function overload defines how the sketch is rendered on the screen. we will eventually save the image and set it as background
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        renderRandom()
        val content : Pair<Int, Int> = this.helper(4)
        val xpos : Int = content.first
        val ypos : Int = content.second
        canvas?.drawText(xpos.toString().plus(ypos.toString()), 200f, 100f, my_paint)
//        canvas?.drawLine(10f,10f,120f,120f, my_paint)
    }

    private fun renderRandom(){
        // todo create a random function for rendering each style and colors
    }

    // the helper functions should just return values that the main function will render
    private fun renderCircle() {

    }
    private fun helper(location_number: Int): Pair<Int, Int> {
        // given the part of a square as a keypad ( 1-9 ), the function returns x,y co ordinates. for eg: 3 - width, 0
        var location_map : List<Pair<Int, Int>> = listOf(
            Pair(0,0),Pair(0,1),Pair(0,2),
            Pair(1,0),Pair(1,1),Pair(1,2),
            Pair(2,0),Pair(2,1),Pair(2,2)
        )
        val actual_location : Int = location_number - 1
        val res : Pair<Int, Int> = location_map.get(actual_location)
        return res
    }
}