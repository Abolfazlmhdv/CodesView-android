package sheetdialog

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.*
import android.widget.LinearLayout
import android.widget.ScrollView
import com.example.lirogramapps.R

class CustomzeSheet(private val context: Context) {

    private lateinit var dialog: Dialog
    private var isExpanded = false
    private var animating = false
    @SuppressLint("MissingInflatedId")
    fun show() {
        val layoutsheet1 = LayoutInflater.from(context).inflate(R.layout.itembottomsjeet, null, false)

        val displayMetrics = context.resources.displayMetrics
        val halfHeight = (displayMetrics.heightPixels * 0.5).toInt()

        val bottom_sheet_root = layoutsheet1.findViewById<LinearLayout>(R.id.bottom_sheet_root)
        val params = bottom_sheet_root.layoutParams ?: ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            halfHeight
        )
        params.height = halfHeight
        bottom_sheet_root.layoutParams = params

        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutsheet1)

        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.WHITE))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setGravity(Gravity.BOTTOM)
            attributes.windowAnimations = R.style.SlideUpDialogAnimation
        }

        dialog.show()
    }
    }