package com.lynas.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.toast

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.some_act)

        val button = findViewById(R.id.button2) as Button
        val imageView = findViewById(R.id.imageView2) as ImageView

        button.setOnClickListener {
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
}
