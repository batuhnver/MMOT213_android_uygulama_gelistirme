package com.example.ncs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    private var x = 0
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress.setVisibility(View.INVISIBLE)
        signUp.setOnClickListener{
            progress.setVisibility(View.VISIBLE)
            x = progress!!.progress
            Thread(Runnable {
                while (x < 100) {
                    x += 5
                    handler.post(Runnable {
                        progress!!.progress = x
                        progressBarTxt!!.text = x.toString() + "/" + progress!!.max
                    })
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                if (x == 100) {
                    progress.setVisibility(View.INVISIBLE)
                }
            }).start()
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        signIn.setOnClickListener{
            progress.setVisibility(View.VISIBLE)
            x = progress!!.progress
            Thread(Runnable {
                while (x < 100) {
                    x += 5
                    handler.post(Runnable {
                        progress!!.progress = x
                        progressBarTxt!!.text = x.toString() + "/" + progress!!.max
                    })
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                if (x == 100) {
                    progress.setVisibility(View.INVISIBLE)
                }
            }).start()
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
        }
    }
}
