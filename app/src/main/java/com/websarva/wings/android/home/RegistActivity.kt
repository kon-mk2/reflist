package com.websarva.wings.android.home

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class RegistActivity: AppCompatActivity() {

    companion object{
        private const val TABLE_NAME="memos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.memo1)


        val helper = DBHelper(this)
        val textTitle = findViewById<EditText>(R.id.text_title)
        val textContent = findViewById<EditText>(R.id.text_content)
        val memoId: Long = intent.getLongExtra("id",0)
        if (memoId != 0L) {
            helper.readableDatabase.use {
                    db -> db.query(TABLE_NAME, arrayOf("id", "title", "content"), "id = ?", arrayOf(memoId.toString()), null, null, null, "1")
                .use { cursor ->
                    if (cursor.moveToFirst()) {
                        textTitle.setText(cursor.getString(1))
                        textContent.setText(cursor.getString(2))
                    }
                }
            }
        }

        findViewById<Button>(R.id.save_button).setOnClickListener{
            helper.writableDatabase.use {
                    db ->
                val values = ContentValues().apply {
                    put("title", textTitle.text.toString())
                    put("Content", textContent.text.toString())
                }
                if (memoId != 0L) {
                    db.update(TABLE_NAME, values,"id = ?", arrayOf(memoId.toString()))
                } else {
                    db.insert(TABLE_NAME,null, values)
                }
            }
            finish()
        }

        findViewById<Button>(R.id.delete_button).setOnClickListener {
            helper.writableDatabase.use {
                    db ->
                db.delete(TABLE_NAME, "id = ?", arrayOf(memoId.toString()))
                Toast.makeText(this, "削除しました", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            finish()
        }
    }
}