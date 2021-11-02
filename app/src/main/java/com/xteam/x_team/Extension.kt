package com.xteam.x_team

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager

fun Activity.hideBar(){
    if (Build.VERSION.SDK_INT < 16) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    else{
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        //just inCase the actionBar is present
        actionBar?.hide()
    }

}