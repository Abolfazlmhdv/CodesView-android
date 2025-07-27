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
import com.example.lirogramapps.R

class CustomzeSheet(private val context: Context) {

    private lateinit var dialog: Dialog
    private var isExpanded = false
    private var animating = false

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
    fun show() {
        val layoutsheet1 = LayoutInflater.from(context).inflate(R.layout.itembottomsjeet, null, false)

        val displayMetrics = context.resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels
        val halfHeight = (screenHeight * 0.5).toInt()

        val bottom_sheet_root = layoutsheet1.findViewById<LinearLayout>(R.id.bottom_sheet_root)
        val params = bottom_sheet_root.layoutParams ?: ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            halfHeight
        )
        params.height = halfHeight
        bottom_sheet_root.layoutParams = params

        // گوشه گرد سفید
        val drawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.WHITE)
            cornerRadii = floatArrayOf(40f, 40f, 40f, 40f, 0f, 0f, 0f, 0f)
        }
        bottom_sheet_root.background = drawable

        // لمس برای کشیدن بالا / پایین
        bottom_sheet_root.setOnTouchListener(object : View.OnTouchListener {
            var startY = 0f
            var lastDeltaY = 0f

            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                if (animating) return false
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startY = event.rawY
                        return true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        lastDeltaY = event.rawY - startY
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        when {
                            lastDeltaY < -50 -> { // کشیدن به بالا
                                if (!isExpanded) {
                                    expandToFullScreen(bottom_sheet_root, screenHeight)
                                    isExpanded = true
                                }
                            }
                            lastDeltaY > 50 -> { // کشیدن به پایین
                                if (isExpanded) {
                                    shrinkToHalfScreen(bottom_sheet_root, halfHeight)
                                    isExpanded = false
                                } else {
                                    dialog.dismiss()
                                }
                            }
                        }
                        return true
                    }
                }
                return false
            }
        })

        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutsheet1)

        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, halfHeight)
            setGravity(Gravity.BOTTOM)
            attributes.windowAnimations = R.style.SlideUpDialogAnimation

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                insetsController?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                )
            } else {
                @Suppress("DEPRECATION")
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }
        }

        dialog.show()
    }

    private fun expandToFullScreen(view: View, targetHeight: Int) {
        animateHeight(view, view.height, targetHeight)
    }

    private fun shrinkToHalfScreen(view: View, targetHeight: Int) {
        animateHeight(view, view.height, targetHeight)
    }

    private fun animateHeight(view: View, from: Int, to: Int) {
        animating = true
        val animator = ValueAnimator.ofInt(from, to)
        animator.duration = 300
        animator.addUpdateListener {
            val value = it.animatedValue as Int
            view.layoutParams.height = value
            view.requestLayout()
        }
        animator.doOnEnd {
            animating = false
        }
        animator.start()
    }

    private fun ValueAnimator.doOnEnd(action: () -> Unit) {
        this.addListener(object : android.animation.Animator.AnimatorListener {
            override fun onAnimationEnd(animation: android.animation.Animator?) = action()
            override fun onAnimationStart(animation: android.animation.Animator?) {}
            override fun onAnimationCancel(animation: android.animation.Animator?) {}
            override fun onAnimationRepeat(animation: android.animation.Animator?) {}
        })
    }
}