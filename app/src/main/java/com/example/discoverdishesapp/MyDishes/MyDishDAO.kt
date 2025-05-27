package com.example.discoverdishesapp.MyDishes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.discoverdishesapp.DatabaseManager

class MyDishDAO (private val context: Context) {
    private lateinit var db: SQLiteDatabase
    private fun open(){
        db = DatabaseManager(context).writableDatabase
    }
    private fun close(){
        db.close()
    }
    //create actions

    //Insert
    fun insert(myDish: MyDish){
        open()
        try {
            val values = ContentValues().apply {
                put(MyDish.COLUMN_NAME_NAME,myDish.name)
                put(MyDish.COLUMN_NAME_INGREDIENTS,myDish.ingredients)
                put(MyDish.COLUMN_NAME_INSTRUCTIONS,myDish.instructions)
                put(MyDish.COLUMN_NAME_DIFFICULT,myDish.difficult)
                put(MyDish.COLUMN_NAME_TIME,myDish.time)
                put(MyDish.COLUMN_NAME_RATING,myDish.rating)
                put(MyDish.COLUMN_NAME_IMAGE,myDish.image)

            }
            val newRowId = db.insert(MyDish.TABLE_NAME,null,values)
            Log.i("DATABASE", "Inserted a dish with id: $newRowId")
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            close()
        }

    }
    //Update
    fun update(myDish: MyDish){
        open()
        try {
            val values = ContentValues().apply {
                put(MyDish.COLUMN_NAME_NAME,myDish.name)
                put(MyDish.COLUMN_NAME_INGREDIENTS,myDish.ingredients)
                put(MyDish.COLUMN_NAME_INSTRUCTIONS,myDish.instructions)
                put(MyDish.COLUMN_NAME_DIFFICULT,myDish.difficult)
                put(MyDish.COLUMN_NAME_TIME,myDish.time)
                put(MyDish.COLUMN_NAME_RATING,myDish.rating)
                put(MyDish.COLUMN_NAME_IMAGE,myDish.image)
            }
            //which row to update, based on the id
            val selection= "${MyDish.COLUMN_NAME_ID} = ${myDish.id}"
            val count = db.update(MyDish.TABLE_NAME,values,selection,null)
            Log.i("DATABASE", "Update dish with id: ${myDish.id}")
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            close()
        }

    }
    //Delete
    fun delete(myDish: MyDish){
        open()
        try {
            val selection = "${MyDish.COLUMN_NAME_ID}=${myDish.id}"
            val deleteRows = db.delete(MyDish.TABLE_NAME,selection,null)
            Log.i("DATABASE", "Delete dish with id: ${myDish.id}")
        }catch (e: Exception){
            e.printStackTrace()
        } finally {
            close()
        }

    }
    //Find by Id
    fun findById(id: Long) : MyDish?{
        open()
        var myDish: MyDish? = null

        try {
            val projection = arrayOf(
                MyDish.COLUMN_NAME_ID,
                MyDish.COLUMN_NAME_NAME,
                MyDish.COLUMN_NAME_INGREDIENTS,
                MyDish.COLUMN_NAME_INSTRUCTIONS,
                MyDish.COLUMN_NAME_DIFFICULT,
                MyDish.COLUMN_NAME_TIME,
                MyDish.COLUMN_NAME_RATING,
                MyDish.COLUMN_NAME_IMAGE)

            val selection ="${MyDish.COLUMN_NAME_ID} = $id"
            val cursor = db.query(
                MyDish.TABLE_NAME,
                projection,
                selection,
                null,
                null,
                null,
                null)
            if (cursor.moveToNext()){
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_NAME))
                val ingredients = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_INGREDIENTS))
                val instructions = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_INSTRUCTIONS))
                val difficult = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_DIFFICULT))
                val time = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_TIME))
                val rating = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_RATING))
                val image = cursor.getBlob(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_IMAGE))

                myDish = MyDish(id,name,ingredients,instructions,difficult,time,rating,image)
            }
            cursor.close()
        } catch (e:Exception){
            e.printStackTrace()
        } finally {
            close()
        }

        return myDish
    }
    //Find all
    fun findAll(): List<MyDish>{
        open()
        var myDishList: MutableList<MyDish> = mutableListOf()
        try {

            val projection = arrayOf(
                MyDish.COLUMN_NAME_ID,
                MyDish.COLUMN_NAME_NAME,
                MyDish.COLUMN_NAME_INGREDIENTS,
                MyDish.COLUMN_NAME_INSTRUCTIONS,
                MyDish.COLUMN_NAME_DIFFICULT,
                MyDish.COLUMN_NAME_TIME,
                MyDish.COLUMN_NAME_RATING,
                MyDish.COLUMN_NAME_IMAGE
            )
            //val selection ="id = task.id
            val selection = null
            val cursor = db.query(
                MyDish.TABLE_NAME,
                projection,
                selection,
                null,
                null,
                null,
                null,)
            while (cursor.moveToNext()){
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_NAME))
                val ingredients = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_INGREDIENTS))
                val instructions = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_INSTRUCTIONS))
                val difficult = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_DIFFICULT))
                val time = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_TIME))
                val rating = cursor.getString(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_RATING))
                val image = cursor.getBlob(cursor.getColumnIndexOrThrow(MyDish.COLUMN_NAME_IMAGE))


                val myDish = MyDish(id,name,ingredients,instructions,difficult,time,rating,image)
                myDishList.add(myDish)
            }
            cursor.close()
        } catch (e:Exception){
            e.printStackTrace()
        } finally {
            close()
        }
        return myDishList
    }



}