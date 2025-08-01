package com.example.lirogramnotes

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import kotlin.math.max

class CustomBottomSheet(private val context: Context) {
    private lateinit var dialog: Dialog
    private var downY = 0f

    @SuppressLint("MissingInflatedId", "InflateParams", "ClickableViewAccessibility")
    fun show() {
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)

        val view = LayoutInflater.from(context).inflate(R.layout.layout_item_note, null)
        dialog.setContentView(view)

        val root = view.findViewById<View>(R.id.bottomsheetdialog)

        val window = dialog.window
        window?.setGravity(Gravity.BOTTOM)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.attributes?.windowAnimations = R.style.BottomSheetAnimation

        val displayMetrics = context.resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels
        val halfHeight = (screenHeight * 0.5).toInt()
        window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, halfHeight)

        root.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    downY = event.rawY
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    val deltaY = event.rawY - downY
                    v.translationY = max(0f, deltaY)
                    true
                }

                MotionEvent.ACTION_UP -> {
                    if (v.translationY > 200) {
                        dismiss()
                    } else {
                        v.animate().translationY(0f).setDuration(200).start()
                    }
                    true
                }

                else -> false
            }
        }

        dialog.show()
    }

    fun dismiss() {
        if (::dialog.isInitialized) dialog.dismiss()
    }
}
