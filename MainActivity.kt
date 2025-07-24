package com.example.lirogramapps

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import android.widget.StackView

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.w3c.dom.Text
import java.util.Stack
class MainActivity : AppCompatActivity() {
    private lateinit var container: FrameLayout
    private val viewstack =  Stack<View>()
    private lateinit var clickme: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)

        val avtivity_sound  = layoutInflater.inflate(R.layout.activity_sound,container,false)
        pushView(avtivity_sound)


        val clicklines: LinearLayout = findViewById(R.id.clicklines)


        clicklines.setOnClickListener{v ->
            val activity_main2 = layoutInflater.inflate(R.layout.activity_main2,null)
          pushView(activity_main2)

            activity_main2.post{
                animationslidein(activity_main2)
            }

            val textlines: TextView = findViewById(R.id.textlines)

            textlines.setOnClickListener{v ->
                val views2 = layoutInflater.inflate(R.layout.activity_sound,null)
                pushView(avtivity_sound)
            }
        }


    }

    fun animationslidein(view: View) {
        view.width.toFloat()
        val animatior = ObjectAnimator.ofFloat(view,"translationX",0f)
        animatior.start()
        animatior.duration = 250
    }


    private fun pushView(view: View) {
        if (viewstack.isNotEmpty()) {
            container.removeView(viewstack.peek())
        }  else {
            viewstack.push(view)
            container.addView(view)
        }
    }   private fun popView(): Boolean {
        if (viewstack .size >1) {
            container.removeView(viewstack.pop())
            container.addView(viewstack.peek())
            return true
        } else {
            return false
        }
    }


    override fun onBackPressed() {
        if (!popView()) {

            super.onBackPressed()
        }
    }
}
