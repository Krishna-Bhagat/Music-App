package com.example.musicapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myrecyclerView: RecyclerView
    lateinit var myadapter: MyAdaptor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java);

        val retrofitData = retrofit.getData("eminem")

        myrecyclerView = findViewById(R.id.recyclerView)

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
                val dataList = p1.body()?.data!!
                myadapter = MyAdaptor(this@MainActivity, dataList)
                myrecyclerView.adapter = myadapter

                myrecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("On response", dataList.toString())
            }

            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                Log.d("On failure", p1.message.toString())
            }
        })
    }
}