package com.example.random_art_generator

import android.widget.ImageView
import android.graphics.Canvas
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import kotlin.math.min

class MyCanvasView @JvmOverloads constructor(context: Context, attrs: AttributeSet?= null, defStyleAttr: Int=0) : ImageView(context, attrs, defStyleAttr) {
    private lateinit var my_canvas: Canvas

    private var drawcolor = ResourcesCompat.getColor(resources, R.color.white, null)
    private var my_paint = Paint().apply {
        color = drawcolor
        style = Paint.Style.FILL
    }
    private var ww: Int = 0
    private var wh: Int = 0
    private var max_possible_width: Int = min(ww, wh)
    private var minwidth: Int = 50
    private var segment_size: Int = 0
    private var segments: Int = 20
    private var segmentList: List<Segment> = listOf()

    private var minheight: Int = 50

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // todo rerender the data with the updated sizes
        super.onSizeChanged(w, h, oldw, oldh)
        this.ww = w
        this.wh = h
        segmentList = createSegments()
        this.calculateSegmentSize()
    }

    // this function overload defines how the sketch is rendered on the screen. we will eventually save the image and set it as background
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        renderRandom()
        val content: Pair<Int, Int> = this.helper(4)
        val xpos: Int = content.first
        val ypos: Int = content.second
        this.renderCircle(canvas)
        //canvas?.drawText(xpos.toString().plus(ypos.toString()), 200f, 100f, my_paint)
//        canvas?.drawLine(10f,10f,120f,120f, my_paint)
    }

    private fun createSegments() {

    }
}

class Segment constructor(xpos: Int, ypos: Int, width: Int, height: Int){

    private fun calculateSegmentSize() {
        if (max_possible_width/segments < minwidth)
        {
            segment_size = minwidth
        }
        else
        {
            segment_size = minwidth
        }
    }

    private fun show(){
       // split the screen into panes

    }

    private fun renderRandom(){
        // todo create a random function for rendering each style and colors
    }

    // the helper functions will take the canvas element and draw the shape in it
    private fun renderCircle(canvas: Canvas?) {
        canvas?.drawCircle(100f, 200f, 100f, my_paint)
    }
    private fun renderTriangle(canvas: Canvas?) {

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