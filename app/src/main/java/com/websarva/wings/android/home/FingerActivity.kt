package com.websarva.wings.android.home

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class FingerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.finger)

        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
    }
}
