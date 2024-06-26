package com.example.dailytask

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "tasks"
val COL_NAME = "taskname"
val COL_DESCRIPTION = "taskdes"
val COL_DATE = "date"
val COL_CATEGORY = "category"
val COL_ID = "id"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_NAME TEXT," +  // Changed to TEXT for simplicity
                "$COL_DESCRIPTION TEXT,"+
                "$COL_DATE TEXT,"+
                "$COL_CATEGORY TEXT)"


        db?.execSQL(createTable)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun insertdata(task:Task){
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put(COL_NAME,task.taskname)
        cv.put(COL_DESCRIPTION,task.taskdes)
        cv.put(COL_DATE,task.date)
        cv.put(COL_CATEGORY,task.category)

        try{
            val result=db.insert(TABLE_NAME,null,cv)
            if (result == -1L) {
                Toast.makeText(context, "Failed to insert data", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Data inserted successfully", Toast.LENGTH_SHORT).show()
            }
        }catch(e:Exception){
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }finally {
            db.close()
        }
    }
fun readdata():MutableList<Task>{
    var list:MutableList<Task> =ArrayList()

    val db=this.readableDatabase
    val query= "Select * from " + TABLE_NAME
    val result=db.rawQuery(query,null)
    if(result.moveToFirst()){
        do{
           var task=Task()
            task.id=result.getString(result.getColumnIndex(COL_ID)).toInt()
            task.taskname=result.getString(result.getColumnIndex(COL_NAME))
            task.taskdes=result.getString(result.getColumnIndex(COL_DESCRIPTION))
            task.date=result.getString(result.getColumnIndex(COL_DATE))
            task.category=result.getString(result.getColumnIndex(COL_CATEGORY))
            list.add(task)
        }while(result.moveToNext())
    }
    result.close()
    db.close()
    return list
}
fun deleteData(){
    val db=this.writableDatabase

    db.delete(TABLE_NAME, null,null)
    db.close()
}


    fun updatedata(task: Task) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COL_NAME, task.taskname)
        cv.put(COL_DESCRIPTION, task.taskdes)
        cv.put(COL_DATE, task.date)
        cv.put(COL_CATEGORY, task.category)

        try {
            val result = db.update(TABLE_NAME, cv, "$COL_ID=?", arrayOf(task.id.toString()))
            if (result > 0) {
                Toast.makeText(context, "Data updated successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to update data", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        } finally {
            db.close()
        }
    }


}
