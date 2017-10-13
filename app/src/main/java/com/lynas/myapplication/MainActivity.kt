package com.lynas.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import org.jetbrains.anko.*
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    private val imgStr = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpBnFZXQbe8rSeORasRchEU4LBr59MhyTOySPLK8UiReMViDESNw"
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            button("Show Image") {
                setOnClickListener {
                    doAsync {
                        val bitmap = getBitmapFromURL(imgStr)
                        runOnUiThread {
                            if (bitmap == null) {
                                toast("unable to get image")
                                return@runOnUiThread

                            }
                            imageView.imageBitmap = bitmap
                        }
                    }
                }
            }
            imageView = imageView {

            }.lparams {
                width = 200
                height = 200
            }
        }


    }


    private fun getBitmapFromURL(imgUrl: String): Bitmap? {
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
}
