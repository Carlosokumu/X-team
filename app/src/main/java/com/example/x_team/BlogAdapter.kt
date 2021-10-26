package com.example.x_team

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter() : RecyclerView.Adapter<BlogAdapter.vH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vH {
        val inflater=LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.row_blogs,null)
        return  vH(v)
    }

    override fun onBindViewHolder(holder: vH, position: Int) {

    }

    override fun getItemCount(): Int {
      return 1
    }
    inner class vH(itemview: View): RecyclerView.ViewHolder(itemview){

        fun bind(){


        }


    }
}