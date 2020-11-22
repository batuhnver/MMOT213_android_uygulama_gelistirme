package com.example.turkey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_city_details.*

class CityDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_details)


        var bundle = intent.extras
        imageView.setImageResource(bundle!!.getInt("image"))
        textView.text = bundle!!.getString("name")
        textView2.text = bundle!!.getString("des")
    }
}
