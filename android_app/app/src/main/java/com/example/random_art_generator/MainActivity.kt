package com.example.random_art_generator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.graphics.Bitmap
import android.app.WallpaperManager
import android.os.Build
import android.widget.TextView
import java.util.*
import android.widget.Toast
import android.widget.ImageView
import android.view.ViewGroup.LayoutParams
import android.view.View.OnClickListener
import androidx.annotation.RequiresApi
import com.example.random_art_generator.MyCanvasView

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import android.os.Environment;



class MainActivity : AppCompatActivity() {
    private var my_bitmap: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    private lateinit var myCanvas: MyCanvasView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_android)
        myCanvas = findViewById(R.id.my_canvas)
        val setWallpaper: Button = findViewById(R.id.set_wallpaper_button)
//        myCanvas.setLayoutParams(LayoutParams(100, 200))

        myCanvas.setOnClickListener (){
            myCanvas.invalidate()
        }
    }

    fun setWallpaper(view: android.view.View) {
        val wallpaperManager = WallpaperManager.getInstance(baseContext)
        print("*****************************J*")
//        print(Build.VERSION.SDK_INT)
        Toast.makeText(this, Build.VERSION.SDK, Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, "hello there", Toast.LENGTH_SHORT).show()
        my_bitmap = myCanvas.getBitmap()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            wallpaperManager.setBitmap(my_bitmap,null,true,WallpaperManager.FLAG_LOCK)
        }
    }


    fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? { // File name like "image.png"
        //create a file to write bitmap data
        var file: File? = null
        return try {
            file = File(Environment.getExternalStorageDirectory().toString() + File.separator + fileNameToSave)
            file.createNewFile()

            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitmapdata = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }
}
