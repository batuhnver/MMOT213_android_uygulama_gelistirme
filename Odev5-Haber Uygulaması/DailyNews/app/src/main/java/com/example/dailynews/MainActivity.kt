package com.example.dailynews

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    var adapter : NewsAdapter? = null
    private lateinit var newsList: List<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //newsApiUrl
        newsApiCallWithUrl("http://newsapi.org/v2/top-headlines?country=tr&apiKey=3aa3d6d9e0a84340bf1404f5d58a678d")



        Handler().postDelayed({
            adapter = NewsAdapter(this, newsList as ArrayList<News>)
            gvNews.adapter = adapter
        }, 2000)

    }

    fun newsApiCallWithUrl(url: String){
        val req = Request.Builder().url(url).build()

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Hata  : ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseStr = response.body?.string()
                val data = JSONObject(responseStr)
                newsList = Gson().fromJson(data.getString("articles"), Array<News>::class.java).toList()
            }
        })
    }
}
