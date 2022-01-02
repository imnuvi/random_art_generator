package com.example.random_art_generator

import android.widget.ImageView
import android.graphics.Canvas
import android.graphics.Color
import android.content.Context
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import kotlin.math.min
import kotlin.math.floor
import kotlin.random.Random

class MyCanvasView @JvmOverloads constructor(context: Context, attrs: AttributeSet?= null, defStyleAttr: Int=0) : ImageView(context, attrs, defStyleAttr) {
    private lateinit var my_canvas: Canvas

    private var drawcolor = ResourcesCompat.getColor(resources, R.color.white, null)
    private var bgcolor = ResourcesCompat.getColor(resources, R.color.black, null)
    private var my_paint = Paint().apply {
        color = drawcolor
        style = Paint.Style.FILL
        setBackgroundColor(bgcolor)
    }
    private var stroke_paint = Paint().apply {
        color = drawcolor
        style = Paint.Style.STROKE
        setColor(drawcolor)
        setStrokeWidth(2F)
        setBackgroundColor(bgcolor)
    }
    private var ww: Int = 0
    private var wh: Int = 0
    private var max_possible_width: Int = min(ww, wh)
    private var minwidth: Int = 50
    private var segment_size: Int = 0
    private var segments: Int = 20
    private var segmentList: MutableList<Segment> = mutableListOf()
    private var segments_x: Int = 0
    private var segments_y: Int = 0

    private var minheight: Int = 50

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // todo rerender the data with the updated sizes
        super.onSizeChanged(w, h, oldw, oldh)
        this.ww = w
        this.wh = h
        segment_size = this.calculateSegmentSize()
        segments_x = ww/segment_size
        segments_y = wh/segment_size
        segmentList = this.createSegments()
    }

    private fun calculateSegmentSize() : Int{
        if (max_possible_width/segments < minwidth)
        {
            segment_size = minwidth
        }
        else
        {
            segment_size = minwidth
        }
        return segment_size
    }

    // this function overload defines how the sketch is rendered on the screen. we will eventually save the image and set it as background
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(10f,10f,120f,120f, my_paint)
        for (i in segmentList){
            i.canvas = canvas
            i.my_paint = my_paint
            i.show()
        }
    }

    private fun createSegments() : MutableList<Segment>{
        var new_segments: MutableList<Segment> = mutableListOf<Segment>()
        var testlist: MutableList<Segment> = mutableListOf<Segment>()
        for (i in 0..segments_x) {
            for (j in 0..segments_y){
                var newsegment : Segment = Segment(i, j, i*segment_size, j*segment_size, segment_size, segment_size, my_paint, stroke_paint)
                new_segments.add(newsegment)
            }
        }

        var newsegment : Segment = Segment(2, 2, 2*segment_size, 2*segment_size, segment_size, segment_size,  my_paint, stroke_paint)
        testlist.add(newsegment)
        return new_segments
    }
}

class Segment constructor(xpos: Int, ypos: Int, truex: Int, truey: Int, width: Int, height: Int, paint: Paint, stroke_paint: Paint){

    public lateinit var canvas: Canvas
    public  var my_paint: Paint = paint
    public  var stroke_paint: Paint = stroke_paint

    public var rand_color: Int = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

    private var width: Int = width
    private var xpos: Int = xpos
    private var ypos: Int = ypos
    private var truex: Int = truex
    public var truey: Int = truey
    private var height: Int = height

    public fun show(){
       // split the screen into panes
        val content: Pair<Int, Int> = this.helper(4)
        val xpos: Int = content.first
        val ypos: Int = content.second
        // canvas.drawText(xpos.toString().plus(ypos.toString()).plus("getoff my lane bruh"), 200f, 100f, my_paint)
        // canvas.drawCircle(100f, 200f, 100f, this.my_paint)
        // this.renderRectangle()
        // this.renderCircle()
        var testlist : List<Int> = listOf(1,2,5)
        this.renderHelper(testlist)
//        var testpath: Path = Path().apply {  }
//        testpath.moveTo(10f, 0f)
//        testpath.lineTo(100f, 0f)
//        testpath.lineTo(100f, 100f)
//        testpath.lineTo(10f, 0f)
//        testpath.close();
//        canvas.drawPath(testpath, my_paint);
    }

    private fun renderRandom(){
        // todo create a random function for rendering each style and colors
    }

    // the helper functions will take the canvas element and draw the shape in it
    private fun renderCircle() {
        my_paint.color = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
        canvas.drawCircle((this.truex + this.width/2 ).toFloat(), (this.truey + this.height/2).toFloat(), (this.width/2).toFloat(), this.my_paint)
    }

    private fun renderRectangle(){
        canvas.drawRect(this.truex.toFloat(), this.truey.toFloat(), 200f, 150f,  this.my_paint)
    }

    private fun renderTriangle() {

    }

    private fun renderHelper(point_list: List<Int>){
        var result_list : MutableList<Pair<Int, Int>> = mutableListOf()
        var shapepath : Path = Path().apply{}
        var iter: Int = 1
        var startx: Float = 0f
        var starty: Float = 0f
        // println(width.toString().plus("       ").plus(height.toString()))
        println("************************************")
        for (point in point_list){
           var (real_x, real_y) = helper(point)
           var (true_x_deviation, true_y_deviation) = Pair(real_x * width, real_y * height)
           var true_vals : Pair<Int, Int> = Pair(truex + true_x_deviation, truey + true_y_deviation)
           var (xloc, yloc) = true_vals
           println(xloc.toString().plus("       ").plus(yloc.toString()))
           // this creates a path for the shape. the shape list should have the locations of the vertices.
           if (iter == 1){
               startx = xloc.toFloat()
               starty = yloc.toFloat()
               shapepath.moveTo(xloc.toFloat(), yloc.toFloat())
           }
           else {
               shapepath.lineTo(xloc.toFloat(), yloc.toFloat())
           }
            iter += 1
       }
        shapepath.lineTo(startx, starty)
        shapepath.close();
        canvas.drawPath(shapepath, my_paint);
    }

    private fun helper(location_number: Int): Pair<Int, Int> {
        // given the part of a square as a keypad ( 1-9 ), the function returns x,y co ordinates. for eg: 3 - width, 0
        var location_map : List<Pair<Int, Int>> = listOf(
            Pair(0,0),Pair(1,0),Pair(2,0),
            Pair(0,1),Pair(1,1),Pair(2,1),
            Pair(0,2),Pair(1,2),Pair(2,2)
        )
//        var location_map : List<Pair<Int, Int>> = listOf(
//            Pair(0,0),Pair(0,1),Pair(0,2),
//            Pair(1,0),Pair(1,1),Pair(1,2),
//            Pair(2,0),Pair(2,1),Pair(2,2)
//        )
        val actual_location : Int = location_number - 1
        val res : Pair<Int, Int> = location_map.get(actual_location)
        return res
    }
}