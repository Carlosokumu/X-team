package com.example.x_team

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import com.example.x_team.MainViewModel as MainViewModel1

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel1>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideBar()
        setContentView(R.layout.activity_main)
        val recyclerView=findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter=BlogAdapter()
        recyclerView.layoutManager=LinearLayoutManager(this)
        DeepLinkRouter.handleRoute(this, Uri.parse("https://youtube.com"))
        //viewModel = ViewModelProvider(this).get(MainViewModel1::class.java)
        viewModel.getBlog().observe(this, Observer {
            it.let {apiModel ->
                run {
                    when (apiModel.status) {
                    Status.SUCCESS ->{
                        

                }
                    Status.FAILURE ->{

                }
                   Status.LOADING ->{

                }
                }
            }
        }
        })
    }
}