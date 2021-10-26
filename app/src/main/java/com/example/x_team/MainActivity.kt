package com.example.x_team

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideBar()
        setContentView(R.layout.activity_main)
        val recyclerView=findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter=BlogAdapter()
        recyclerView.layoutManager=LinearLayoutManager(this)
        DeepLinkRouter.handleRoute(this, Uri.parse("https://youtube.com"))
    }
}