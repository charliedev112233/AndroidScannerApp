package com.example.androidscannerapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getIntOrNull

class SQLiteHelper (context: Context):
SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {


        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Refrigeration.tb"
        private const val TBL_REFRIGERATION = "tbl_refrigeration"
        private const val BULLCODE = "BullCode"
        private const val DATECODE = "DateCode"
        private const val SQL_CREATE_ENTRIES = ("CREATE TABLE" + TBL_REFRIGERATION + DATECODE + "Primary Key"
        + BULLCODE + "text")

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TBL_REFRIGERATION}"


    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTblRefrigeration = ("CREATE TABLE" + TBL_REFRIGERATION + DATECODE + "Primary Key"
                + BULLCODE + "text")

        db?.execSQL(SQL_CREATE_ENTRIES)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }


    fun insertBull(std: BullModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DATECODE, std.dateCode)
        contentValues.put(BULLCODE, std.bullCode)


        val success = db.insert(TBL_REFRIGERATION, null, contentValues)
        db.close()
        return success
    }

    fun getAllBull(): ArrayList<BullModel>
    {

        val stdList: ArrayList<BullModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_REFRIGERATION"
        val db = this.readableDatabase
        val cursor: Cursor?


        try{
            cursor = db.rawQuery(selectQuery, null)
        }
        catch(e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()

        }




        var dateCode: Int
        var bullCode: String

if (cursor.moveToFirst())

{

    do{
        dateCode = cursor.getInt(cursor.getColumnIndexOrThrow("dateCode"))
        bullCode = cursor.getString(cursor.getColumnIndexOrThrow("bullCode"))

        val std = BullModel(dateCode = dateCode, bullCode = bullCode)

        stdList.add(std)

    }while(cursor.moveToNext())


}
        return stdList
    }

    fun deleteBullById(id:Int): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DATECODE, id)

        val success = db.delete(TBL_REFRIGERATION, "id=$BULLCODE", null)
    db.close()

        return success
    }



}





