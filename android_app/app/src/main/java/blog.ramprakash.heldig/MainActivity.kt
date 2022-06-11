package blog.ramprakash.heldig

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.widget.Button
import android.graphics.Bitmap
import android.app.WallpaperManager
import android.os.Build
import android.widget.Toast
import android.view.Menu
import android.view.MenuInflater

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import android.os.Environment;
import android.view.Window
import android.view.WindowManager
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private var my_bitmap: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    private lateinit var bottomNav : BottomNavigationView
    private lateinit var myCanvas: MyCanvasView
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN )
        this.supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_android)
        loadFragment(SettingsFragment())
        bottomNav = findViewById<BottomNavigationView>(R.id.config_menubar)
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.settings -> {
                    loadFragment(SettingsFragment())
                    return@setOnNavigationItemReselectedListener
                }
            }
        }
//        supportActionBar?.title = "xoll"
        supportActionBar?.hide()
        myCanvas = findViewById(R.id.my_canvas)
        val setWallpaper: Button = findViewById(R.id.set_wallpaper_button)
//        myCanvas.setLayoutParams(LayoutParams(100, 200))

        myCanvas.setOnClickListener (){
            myCanvas.invalidate()
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.config_menubar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun setWallpaper(view: android.view.View) {
        val wallpaperManager = WallpaperManager.getInstance(baseContext)
        my_bitmap = myCanvas.getBitmap()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            wallpaperManager.setBitmap(my_bitmap,null,true,WallpaperManager.FLAG_SYSTEM)
            Toast.makeText(this, "Wallpaper set!", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Error occoured when setting wallpaper", Toast.LENGTH_SHORT).show()
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
