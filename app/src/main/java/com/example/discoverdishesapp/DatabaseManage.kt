package com.example.discoverdishesapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_VERSION=1
        const val DATABASE_NAME="MyDish.db"
        //Task
        private const val SQL_CREATE_DISH = "" +
                "CREATE TABLE ${MyDish.TABLE_NAME} (" +
                "${MyDish.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${MyDish.COLUMN_NAME_NAME} TEXT," +
                "${MyDish.COLUMN_NAME_INGREDIENTS} TEXT," +
                "${MyDish.COLUMN_NAME_INSTRUCTIONS} TEXT," +
                "${MyDish.COLUMN_NAME_DIFFICULT} TEXT," +
                "${MyDish.COLUMN_NAME_TIME} TEXT," +
                "${MyDish.COLUMN_NAME_RATING} TEXT)"
        private const val SQL_DELETE_DISH = "DROP TABLE IF EXISTS ${MyDish.TABLE_NAME}"


    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_DISH)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onDestroy(db)
        onCreate(db)
    }
    private fun onDestroy(db: SQLiteDatabase){
        db.execSQL(SQL_DELETE_DISH)

    }
}