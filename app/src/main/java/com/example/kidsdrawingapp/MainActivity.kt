package com.example.kidsdrawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null
    private var mImageButtonCurrentColor: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        drawingView!!.setBrushSize(5f)

        val linearLayoutColorPaint = findViewById<LinearLayout>(R.id.ll_paint_color)
        mImageButtonCurrentColor = linearLayoutColorPaint[1] as ImageButton

        mImageButtonCurrentColor!!.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pressed_pallet)
        )

        val ibBrush: ImageButton = findViewById(R.id.ib_brush)
        ibBrush.setOnClickListener {
            showBrushSizeDialog()
        }

        val ibUndo: ImageButton = findViewById(R.id.ib_undo)
        ibUndo.setOnClickListener {
            drawingView!!.onClickUndo()
        }
    }

    private fun showBrushSizeDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.brush_dialog_size)
        val smallBrush: ImageButton = brushDialog.findViewById(R.id.ib_small_brush)
        smallBrush.setOnClickListener {
            drawingView!!.setBrushSize(10f)
            brushDialog.dismiss()
        }

        val mediumBrush: ImageButton = brushDialog.findViewById(R.id.ib_medium_brush)
        mediumBrush.setOnClickListener {
            drawingView!!.setBrushSize(20f)
            brushDialog.dismiss()
        }

        val largeBrush: ImageButton = brushDialog.findViewById(R.id.ib_large_brush)
        largeBrush.setOnClickListener {
            drawingView!!.setBrushSize(30f)
            brushDialog.dismiss()
        }


        brushDialog.show()

    }

    fun paintClicked(view: View) {

        if (view !== mImageButtonCurrentColor) {
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingView!!.setColor(colorTag)

            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pressed_pallet)
            )

            mImageButtonCurrentColor!!.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.normal_pallet)
            )

            mImageButtonCurrentColor = view

        }
    }
}