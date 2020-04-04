package com.eebros.daggertutorial.view

import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.eebros.daggertutorial.R

class CustomProgressDialog(private val activity: Activity) {

    private val dialog: Dialog = Dialog(activity).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(R.layout.custom_loading_layout)
    }

    fun showDialog() {
        dialog.show()
        animateProgressImage()
    }

    private fun animateProgressImage() {
        val rotate = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 2000
        rotate.interpolator = LinearInterpolator()
        rotate.repeatCount = Animation.INFINITE
        dialog.findViewById<ImageView>(R.id.custom_loading_imageView).startAnimation(rotate)
    }

    fun hideDialog() {
        dialog.dismiss()
        //activity.findViewById<ImageView>(R.id.custom_loading_imageView).clearAnimation()
    }
}