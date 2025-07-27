

/*
//This is the code I assigned for round photos.
 */


package com.example.lirogramapps

import android.content.Context
import android.graphics.*
import android.provider.DocumentsContract
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class imageviewcircle @JvmOverloads constructor(context: Context,attr: AttributeSet? = null,defStyleAttr: Int = 0) :
        AppCompatImageView(context,attr,defStyleAttr)  {

            private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
                private val path = Path()


    override fun onDraw(canvas: Canvas) {
        val raduis = width.coerceAtMost(height) / 1.5f

        val x = width / 1.5f
        val y = height  / 1.5f

        path.reset()
        path.addCircle(x,y,raduis, Path.Direction.CW)
        canvas.clipPath(path)
        canvas.save()


        super.onDraw(canvas)
    }
        }
