package com.websarva.wings.android.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nifcloud.mbaas.core.NCMB
import com.nifcloud.mbaas.core.NCMBException
import com.nifcloud.mbaas.core.NCMBUser


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //**************** APIキーの設定とSDKの初期化 **********************
        NCMB.initialize(
            this.applicationContext,
            "c94312e387c7cb834bfbe2f7532c09c579f771d836723dec9f8ef4e7355571e8",
            "2b02eadb293483cef73444578efbcd2c26c3df38808555912dd698cf9ae9bb70"
        )

        fun onCreateOptionsMenu(menu: Menu?): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

        fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            val id = item.itemId
            if (id == R.id.action_settings) {
                return true
            }
            if (id == R.id.action_logout) {
                NCMBUser.logoutInBackground { e: NCMBException? -> }
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
            return super.onOptionsItemSelected(item)
        }

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)


        val list_button :Button =findViewById(R.id.list_button)
        list_button.setOnClickListener{
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

        val datelist_button :Button =findViewById(R.id.datelist_button)

        datelist_button.setOnClickListener{
            val intent = Intent(this,DatelistActivity::class.java)
            startActivity(intent)
        }

        val memo_button :Button =findViewById(R.id.memo_button)

        memo_button.setOnClickListener{
            val intent = Intent(this,MemoActivity::class.java)
            startActivity(intent)
        }

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
