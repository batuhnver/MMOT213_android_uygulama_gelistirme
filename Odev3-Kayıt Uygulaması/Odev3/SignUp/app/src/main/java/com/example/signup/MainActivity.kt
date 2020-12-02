package com.example.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ad = findViewById(R.id.edTxtAd) as EditText
        var soyad = findViewById(R.id.edTxtSoyad) as EditText
        var yas = findViewById(R.id.edTxtYas) as EditText
        var email = findViewById(R.id.edTxtMail) as EditText
        var sifre = findViewById(R.id.edTxtPw) as EditText
        var sifreTekrar = findViewById(R.id.edTxtPwAgain) as EditText
        var kaydet = findViewById(R.id.btnSave) as Button


        kaydet.setOnClickListener(){
            if(ad.text.isEmpty() )
            Toast.makeText(applicationContext,"Ad Bilgisini Yanlış Girdiniz.!",Toast.LENGTH_SHORT).show()
            else if(soyad.text.isEmpty())
                Toast.makeText(applicationContext,"Soyad Bilgisini Yanlış Girdiniz.!",Toast.LENGTH_SHORT).show()
            else if(yas.text.isEmpty() ||yas.text.toString().toInt() !in 18..100)
                Toast.makeText(applicationContext,"Yaş Bilgisini Yanlış Girdiniz.!",Toast.LENGTH_SHORT).show()
            else if(!emailKontrol(email.text.toString()))
                Toast.makeText(applicationContext,"E Posta Formatı Hatalıdır.!",Toast.LENGTH_SHORT).show()
            else if(sifre.text.toString().trim().length < 5)
                Toast.makeText(applicationContext,"Şifre En Az 5 Karakterden Oluşmalıdır.!",Toast.LENGTH_SHORT).show()
            else if(sifreTekrar.text.toString() != sifre.text.toString())
                Toast.makeText(applicationContext,"Girilen Şifreler Uyuşmuyor.!",Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext,"Kayıt Başarılı.!",Toast.LENGTH_SHORT).show()

        }
    }
    private fun emailKontrol(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}
