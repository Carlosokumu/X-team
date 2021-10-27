package com.example.x_team

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ViewAnimator
import androidx.recyclerview.widget.RecyclerView

class CustomView(context: Context, attrs: AttributeSet?=null): ViewAnimator(context,attrs) {
    private val contentView: ViewGroup
    private val   failure: View
    private val recyclerView: RecyclerView
    private val loading: View
    companion object {
        const val  POSITION_LOADING=0
        const val  POSITION_CONTENT=1
        const val  POSITION_FAILURE=2
        //Fade animation time
        private const val FADE_DURATION_MS = 200L
    }
    init {
        //get An instance of the inflater to inflate the views
        val inflater= LayoutInflater.from(context)
        //Order matters as we will be displaying children views from top
        loading= inflater.inflate(R.layout.loading_view,this,true)
        val customResId: Int
        context.theme.obtainStyledAttributes(R.styleable.SwitcherView).apply {
            try {
                customResId=getResourceId(R.styleable.SwitcherView_contentView,R.layout.layout_content)
            }
            finally {
                recycle()
            }
        }
        contentView=inflater.inflate(customResId,this,true) as ViewGroup
        recyclerView=contentView.findViewById(R.id.recycler_view)
        failure= inflater.inflate(R.layout.layout_failure,this,true)
        //Apply animation during the switch of the views
        inAnimation = AlphaAnimation(0.0f, 1.0f).apply { duration = FADE_DURATION_MS }
        outAnimation = AlphaAnimation(1.0f, 0.0f).apply { duration = FADE_DURATION_MS }
    }
    fun showApiStatus(apiModel: ApiModel<Any>,adapter: RecyclerView.Adapter<*>?){
        when(apiModel.status){
            Status.LOADING ->{
                showLoading()
            }
            Status.FAILURE ->{
                showFailure()
            }
            Status.SUCCESS ->{
                recyclerView.adapter=adapter
                showContent()
                adapter?.notifyDataSetChanged()
            }
        }
    }
    private fun showLoading() {
        if (displayedChild != POSITION_LOADING) {
            displayedChild = POSITION_LOADING
        }
    }
    private fun showFailure(){
        if (displayedChild != POSITION_FAILURE) {
            displayedChild = POSITION_FAILURE
        }
    }
    private fun showContent(){
        if (displayedChild != POSITION_CONTENT) {
            displayedChild = POSITION_CONTENT
        }
    }
}