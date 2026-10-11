package com.vientosur.radio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = AppCompatTextView(this).apply {
            text = "Vientosur Android"
            textSize = 24f
            setPadding(32, 32, 32, 32)
        }

        setContentView(textView)
    }
}
