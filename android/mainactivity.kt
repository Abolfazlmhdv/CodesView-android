package com.example.lirogramnotes

import Reccyclerview.NoteListAdapter
import Reccyclerview.Notes
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteListAdapter
    private lateinit var widget_click_vert: ImageView
    private lateinit var recyclerview: RecyclerView
    private lateinit var emtyview: RelativeLayout
    private var note = mutableListOf<Notes>()
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val container: FrameLayout = findViewById(R.id.container)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.AppColor)

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = 0
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = 0
        }

        val layout1 = LayoutInflater.from(this).inflate(R.layout.fade_widget_layout1,container,false)
        container.addView(layout1)

        val clicksheet: TextView = layout1.findViewById(R.id.clicksheet)
        emtyview = layout1.findViewById(R.id.emtyview)

        adapter = NoteListAdapter(note)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        clicksheet.setOnClickListener{view -> val sheet = CustomBottomSheet(this)
        sheet.show()}

        widget_click_vert = layout1.findViewById(R.id.widget_click_vert)
        widget_click_vert.setOnClickListener{view -> WindowPopup(view)}
    }
    private fun WindowPopup(anchor: View) {
        val view = LayoutInflater.from(this).inflate(R.layout.layout_itempopup, null)


        val popup = PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        popup.isOutsideTouchable = true
        popup.isFocusable = true
        val widget_click_add: RelativeLayout = view.findViewById(R.id.widget_click_add)

     widget_click_add.setOnClickListener{view ->
         showbottomsheet1(view)
         popup.dismiss()
     }

        popup.showAsDropDown(anchor,0,10)
    }

    @SuppressLint("MissingInflatedId")
    private fun showbottomsheet1(view: View) {
        val view = BottomSheetDialog(this)
        val dialog = layoutInflater.inflate(R.layout.widget_view_snote,null)

        val widget_click_add: EditText = dialog.findViewById(R.id.widget_click_add)
        val widget_caption_send: EditText = dialog.findViewById(R.id.widget_caption_send)
        val widget_send_image: ImageView = dialog.findViewById(R.id.widget_send_image)

        widget_send_image.setOnClickListener{view ->
            val title = widget_click_add.text.toString().trim()
            val caption = widget_caption_send.text.toString().trim()

                // set title and caption lines 94  . . .
            if (title.isNotEmpty() && caption.isNotEmpty()) {
                val newnote = Notes(title,caption)
                note.add(newnote)
                adapter.notifyDataSetChanged()
                widget_click_add.text.clear()
                widget_caption_send.text.clear()
                updateUI()
                Toast.makeText(this,"Succses", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Please Enter Text", Toast.LENGTH_SHORT).show()
            }
        }
        view.setContentView(dialog)
        view.show()
    }

    private fun updateUI() {
        if (note.isNotEmpty()) {
            emtyview.visibility = View.GONE
            recyclerview.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(this,R.anim.slide_in_right)
                    recyclerview.startAnimation(animation)
        } else {
            emtyview.visibility = View.VISIBLE
            recyclerview.visibility = View.GONE
            val animation = AnimationUtils.loadAnimation(this,R.anim.slide_in_right)
            emtyview.startAnimation(animation)
        }
    }

}
