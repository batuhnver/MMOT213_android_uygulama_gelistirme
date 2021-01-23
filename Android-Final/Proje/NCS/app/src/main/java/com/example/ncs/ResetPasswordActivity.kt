package com.example.ncs


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {


    lateinit var  Email : EditText
    lateinit var  ForgetBtn : Button
    lateinit var  Auth : FirebaseAuth
    private var x = 0
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        progress4.setVisibility(View.INVISIBLE)
        Email = findViewById(R.id.edTxtPw)
        ForgetBtn = findViewById(R.id.resetPwBtn)
        Auth = FirebaseAuth.getInstance()

        resetPwBtn.setOnClickListener {
            val email = edTxtPw.text.toString().trim()
            if(email.isEmpty()){
                Email.error = "E-Posta Adresinizi Giriniz.!"
                Toast.makeText(applicationContext,"Lütfen E-Posta Adresinizi Giriniz.!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            forGetPassword(email)
            progress4.setVisibility(View.VISIBLE)
            x = progress4!!.progress
            Thread(Runnable {
                while (x < 100) {
                    x += 5
                    handler.post(Runnable {
                        progress4!!.progress = x
                        progressBarTxt4!!.text = x.toString() + "/" + progress4!!.max
                    })
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                if (x == 100) {
                    progress4.setVisibility(View.INVISIBLE)
                }
            }).start()
        }
    }
    private fun forGetPassword(email: String){
        Auth.sendPasswordResetEmail(email).addOnCompleteListener { task: Task<Void> ->
            if(task.isComplete){

                val intent = Intent(this,SignIn::class.java)
                startActivity(intent)

                Toast.makeText(applicationContext,"Parola Sıfırlama Bağlantınız E-Posta Adresinize Gönderildi.!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
