package com.example.lirogramnotes

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window

class CustomBottomSheet(private val context: Context) {
    private lateinit var dialog: Dialog


    fun show() {

        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)


        val view = LayoutInflater.from(context).inflate(R.layout.layout_item_note,null)
        dialog.setContentView(view)

        val window = dialog.window

        window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        window?.setGravity(Gravity.BOTTOM)

        window?.attributes?.windowAnimations = R.style.BottomSheetAnimation


        val displayMetrics = context.resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels
        val halfHeight = (screenHeight * 0.5).toInt()

        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,halfHeight)
        dialog.show()


    }

    fun dismiss() {
        dialog.dismiss()
    }
}