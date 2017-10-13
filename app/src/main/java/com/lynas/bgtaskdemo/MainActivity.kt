package com.lynas.bgtaskdemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import java.net.HttpURLConnection
import java.net.URL

val image = "https://image.flaticon.com/teams/slug/freepik.jpg"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById(R.id.button) as Button
        val imageView = findViewById(R.id.imageView) as ImageView

        button.setOnClickListener {
            doAsync {
                val bitmap = getBitmapFromURL(image)
                runOnUiThread {
                    if (bitmap == null) {
                        toast("Could not find image")
                        return@runOnUiThread
                    }

                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }
}


fun getBitmapFromURL(imgUrl: String): Bitmap? {
    return try {
        val url = URL(imgUrl)
        val connection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input = connection.inputStream
        BitmapFactory.decodeStream(input)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}