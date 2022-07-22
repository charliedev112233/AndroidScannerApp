package com.example.androidscannerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScannedHistory : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var adapter:BullAdapter?= null
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private var std: BullModel? = null
    private var edbullCode: String = ""
    private  var eddateCode: Int = BullModel.getAutoId()



    private lateinit var sqlitehelper: SQLiteHelper





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanned_history)


        initView()
        initRecyclerView()



        sqlitehelper = SQLiteHelper(this)



        adapter?.setOnClickItem{
            Toast.makeText(this,it.bullCode,Toast.LENGTH_SHORT).show()
       std = it

        }

        adapter?.setOnClickDeleteItem {
            deleteBull(it.dateCode)
        }

    }




    private fun addBull()
    {
     val bullCode = edbullCode
     val dateCode = eddateCode

val std = BullModel(bullCode = bullCode, dateCode = dateCode)

        val status = sqlitehelper.insertBull(std)

        if (status >-1){
            Toast.makeText(this,"Bull Added",Toast.LENGTH_SHORT).show()
        }


    }

    private fun getBulls()
    {


        val stdList = sqlitehelper.getAllBull()
        Log.e("pppp","$(stdList.size)")
        adapter?.addItems(stdList)

    }

    private fun deleteBull(id:Int)
    {


        if(id==null) return
        val builder = AlertDialog.Builder(this)
        builder .setMessage("Do you want to delete record")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") {dialog,_->
            dialog.dismiss()
        }

        sqlitehelper.deleteBullById(id)
        getBulls()

        builder.setNegativeButton("No") {dialog,_->

        }

        val alert = builder.create()
        alert.show()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BullAdapter()
        recyclerView.adapter = adapter

    }


    private fun initView(){

        recyclerView = findViewById(R.id.recyclerView)
    }
}
