package com.example.x_team

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import com.example.x_team.MainViewModel as MainViewModel1

class MainActivity : AppCompatActivity(), OnLinkClicked {
    private val viewModel by viewModel<MainViewModel1>()
    private lateinit var customSwitcher: CustomView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideBar()
        setContentView(R.layout.activity_main)
        val recyclerView=findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter=BlogAdapter(this)
        recyclerView.layoutManager=LinearLayoutManager(this)
        customSwitcher=findViewById<CustomView>(R.id.customSwitcher)
        findViewById<TextView>(R.id.textView2).setOnClickListener {
            this.recreate()
        }
        setUpView()
        //DeepLinkRouter.handleRoute(this, Uri.parse("https://youtube.com"))
        //viewModel = ViewModelProvider(this).get(MainViewModel1::class.java)
        /*
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

         */
    }
    private fun setUpView(){
        val adapter=BlogAdapter(this)
        viewModel.getBlog().observe(this, Observer {
            it.let { apiModel ->
                customSwitcher.showApiStatus(apiModel = apiModel,adapter = adapter)
                if (apiModel.status == Status.SUCCESS) {
                    adapter.setData(it.data!!)
                    //Timber.e(apiModel.data.size.toString())
                }
            }
        })


    }

    override fun Onlink(link: String) {
        DeepLinkRouter.handleRoute(this, Uri.parse(link))
    }
}