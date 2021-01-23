package com.example.ncs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*



class SignIn : AppCompatActivity() {

    private var x = 0
    private val handler = Handler()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = FirebaseAuth.getInstance()
        progress1.setVisibility(View.INVISIBLE)

        signInBtn.setOnClickListener{
            if(mail.text.trim().toString().isNotEmpty() && password.text.trim().toString().isNotEmpty()){
                signIn(mail.text.trim().toString(),password.text.trim().toString())
                progress1.setVisibility(View.VISIBLE)
                x = progress1!!.progress
                Thread(Runnable {
                    while (x < 100) {
                        x += 5
                        handler.post(Runnable {
                            progress1!!.progress = x
                            progressBarTxt1!!.text = x.toString() + "/" + progress1!!.max
                        })
                        try {
                            Thread.sleep(100)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                    if (x == 100) {
                        progress1.setVisibility(View.INVISIBLE)
                    }
                }).start()
            }else{
                Toast.makeText(this,"Hatalı Bilgiler Girdiniz..!",Toast.LENGTH_SHORT).show()
            }
        }

        forgotPw.setOnClickListener{
            progress1.setVisibility(View.VISIBLE)
            x = progress1!!.progress
            Thread(Runnable {
                while (x < 100) {
                    x += 5
                    handler.post(Runnable {
                        progress1!!.progress = x
                        progressBarTxt1!!.text = x.toString() + "/" + progress1!!.max
                    })
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                if (x == 100) {
                    progress1.setVisibility(View.INVISIBLE)
                }
            }).start()
            val intent = Intent(this,ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    fun signIn(mail:String,password:String){
        auth.signInWithEmailAndPassword(mail,password)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Giriş Başarılı. Hoşgeldiniz.!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,NcsActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Kullanıcı Bulunamadı.!!",Toast.LENGTH_SHORT).show()
                }
            }
    }


}






















































