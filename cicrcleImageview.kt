package com.example.lirogramapps



import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        val radius = width.coerceAtMost(height) / 2f

        val cx = width / 2f
        val cy = height / 2f

        // Clip the canvas to a circle path
        path.reset()
        path.addCircle(cx, cy, radius, Path.Direction.CW)
        canvas.clipPath(path)

        // Draw the image normally but clipped in circle
        super.onDraw(canvas)

        // Optional: draw a circular border
        // paint.style = Paint.Style.STROKE
        // paint.color = Color.WHITE
        // paint.strokeWidth = 4f
        // canvas.drawCircle(cx, cy, radius - 2f, paint)
    }
}
