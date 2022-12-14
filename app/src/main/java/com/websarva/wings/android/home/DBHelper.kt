package com.websarva.wings.android.home

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context?): SQLiteOpenHelper(context, DBNAME, null, version) {
    companion object {
        private const val DBNAME = "DBSample.sqlite"
        private const val version = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.let{
            it.execSQL("create table memos (id integer primary key, title text, content text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }
}