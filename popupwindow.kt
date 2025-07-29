package PopupWindow

import User.WidgetView
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.Toast
import java.security.cert.TrustAnchor

// import R
import com.example.lirogramnotes.R

class popupwindow(private val context: Context) {
    private lateinit var widgetView: WidgetView

    private  var popupwindow: popupwindow? = null


    @SuppressLint("MissingInflatedId")
    fun showpopup(anchor: View) {
        val inflater = LayoutInflater.from(context)
        val popupwindow = inflater.inflate(R.layout.layout_itempopup,null)

        val poup = PopupWindow(anchor,
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,true
        )

        val widget_click_add: RelativeLayout = popupwindow.findViewById(R.id.widget_click_add)

        widget_click_add.setOnClickListener{ view -> Toast.makeText(context,"Coming Soon", Toast.LENGTH_SHORT).show() }

        poup?.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        poup?.showAsDropDown(anchor)
    }


}