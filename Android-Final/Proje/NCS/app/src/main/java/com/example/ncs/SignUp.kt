package com.example.ncs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUp : AppCompatActivity() {

    private var x = 0
    private val handler = Handler()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        progress2.setVisibility(View.INVISIBLE)
        auth = FirebaseAuth.getInstance()

        registerBtn.setOnClickListener{
            if(email.text.trim().toString().isNotEmpty() && passwd.text.trim().toString().isNotEmpty() && passwd.text.trim().toString() == againPasswd.text.trim().toString()){
                kullaniciOlustur(email.text.trim().toString(), passwd.text.trim().toString())
                progress2.setVisibility(View.VISIBLE)
                x = progress2!!.progress
                Thread(Runnable {
                    while (x < 100) {
                        x += 5
                        handler.post(Runnable {
                            progress2!!.progress = x
                            progressBarTxt2!!.text = x.toString() + "/" + progress2!!.max
                        })
                        try {
                            Thread.sleep(100)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                    if (x == 100) {
                        progress2.setVisibility(View.INVISIBLE)
                    }
                }).start()
            }else if(againPasswd.text.toString() != passwd.text.toString()){
                Toast.makeText(applicationContext,"Girilen Şifreler Uyuşmuyor.!",Toast.LENGTH_SHORT).show()
            }
           else{
                Toast.makeText(this,"Hatalı Bilgi Girdiniz..!",Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun kullaniciOlustur(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Başarıyla Kayıt Oldunuz..!",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,SignIn::class.java)
                    startActivity(intent)
                }else if(passwd.text.trim().length < 8){
                    Toast.makeText(this,"Şifreniz En Az 8 Karakterden Oluşmalıdır..!",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"E-Posta Adres Formatınız Hatalı..!",Toast.LENGTH_SHORT).show()
                }
            }
    }
}
