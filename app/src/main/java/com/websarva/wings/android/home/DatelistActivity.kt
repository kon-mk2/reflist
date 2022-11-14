package com.websarva.wings.android.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DatelistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datelist)

        val home_bar :Button =findViewById(R.id.home_bar)

        home_bar.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
        val rif_bar :Button =findViewById(R.id.rif_bar)

        rif_bar.setOnClickListener{
            val intent = Intent(this,SubActivity::class.java)
            startActivity(intent)
        }
        val date_bar :Button =findViewById(R.id.date_bar)

        date_bar.setOnClickListener{
            val intent = Intent(this,DatelistActivity::class.java)
            startActivity(intent)
        }
        val memo_bar :Button =findViewById(R.id.memo_bar)

        memo_bar.setOnClickListener{
            val intent = Intent(this,MemoActivity::class.java)
            startActivity(intent)
        }
        val user_bar :Button =findViewById(R.id.user_bar)

        user_bar.setOnClickListener{
            val intent = Intent(this,UserActivity::class.java)
            startActivity(intent)
        }
    }
}
