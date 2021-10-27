package com.example.x_team

import android.annotation.SuppressLint
import android.media.Image
import android.transition.Slide
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class BlogAdapter(val onLinkClicked: OnLinkClicked) : RecyclerView.Adapter<BlogAdapter.vH>() {

    private var response: List<Response> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vH {
        val inflater=LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.row_blogs,null)
        return  vH(v)
    }
    fun setData(response: List<Response>){
        this.response=response
    }

    override fun onBindViewHolder(holder: vH, position: Int) {
        holder.bind(response)
    }

    override fun getItemCount(): Int {
      return response.size
    }
    inner class vH(val itemview: View): RecyclerView.ViewHolder(itemview){
        var imageView=itemView.findViewById<ImageSlider>(R.id.image)
        var title=itemview.findViewById<TextView>(R.id.title)
        var desc=itemview.findViewById<TextView>(R.id.desc)

        fun bind(response: List<Response>){
            val list=ArrayList<SlideModel>()
            list.add(SlideModel(response[adapterPosition].imageurl))
            imageView.setImageList(list,ScaleTypes.FIT)

          itemview.setOnClickListener {
              onLinkClicked.Onlink(response[adapterPosition].link)
          }
          //Glide.with(itemView.context).load(response[adapterPosition]).into(imageView)
            title.text=response[adapterPosition].title
            desc.text=response[adapterPosition].headLine
        }



    }
}