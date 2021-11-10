package com.xteam.x_team

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xteam.x_team.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel
import com.xteam.x_team.MainViewModel as MainViewModel1

class MainActivity : AppCompatActivity(), OnLinkClicked {
    private val viewModel by viewModel<MainViewModel1>()
    private lateinit var customSwitcher: CustomView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideBar()
      //  setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val recyclerView=findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter=BlogAdapter(this)
        recyclerView.layoutManager=LinearLayoutManager(this)
        customSwitcher=findViewById<CustomView>(R.id.customSwitcher)
        findViewById<TextView>(R.id.textView2).setOnClickListener {
            this.recreate()
        }
        setUpView()
  
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