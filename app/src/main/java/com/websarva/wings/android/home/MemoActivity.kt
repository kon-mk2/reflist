package com.websarva.wings.android.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.memo)
        val add = findViewById<ImageButton>(R.id.add)
        val listView = findViewById<ListView>(R.id.listView)
        setListViewAdapter(listView)


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
        val data = listOf(
            ListItem(1, "メモタイトル1"),
            ListItem(2, "メモタイトル2"),
            ListItem(3, "メモタイトル3"),
        )

        add.setOnClickListener {
            val intent = Intent(this, RegistActivity::class.java)
            startActivity(intent)
        }

        listView.setOnItemClickListener { av, _, position, id ->
            val intent = Intent(this, RegistActivity::class.java)
            val itemId = listView.adapter.getItemId(position)
            intent.putExtra("id", itemId)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val helper = DBHelper(this)
        val listView = findViewById<ListView>(R.id.listView)
        setListViewAdapter(listView)
    }

    fun setListViewAdapter(listView: ListView)
    {
        val helper = DBHelper(this)
        helper.readableDatabase.use {
                db -> db.query("memos", arrayOf("id", "title", "content"),null,null,null,null,"id DESC",null)
            .use { cursor ->
                val memoList = mutableListOf<ListItem>()
                if (cursor.moveToFirst()) {
                    for (i in 1..cursor.count) {
                        val memoId = cursor.getInt(0)
                        val title = cursor.getString(1)
                        memoList.add(ListItem(memoId.toLong(), title))
                        cursor.moveToNext()
                    }
                }
                listView.adapter = CustomListAdapter(this, memoList, R.layout.list_item)
            }
        }
    }
}