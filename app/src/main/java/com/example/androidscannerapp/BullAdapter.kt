package com.example.androidscannerapp

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class BullAdapter : RecyclerView.Adapter<BullAdapter.BullViewHolder>() {
private var stdList: ArrayList<BullModel> = ArrayList()
    private var OnClickItem:((BullModel)->Unit)? = null
    private var OnClickDeleteItem: ((BullModel)->Unit)? = null




    fun setOnClickItem(callback: (BullModel) -> Unit){
this.OnClickItem = callback
    }


    fun addItems(items: ArrayList<BullModel>) {

        this.stdList = items
        notifyDataSetChanged()
    }

fun setOnClickDeleteItem(callback:(BullModel)->Unit)
{
    this.OnClickDeleteItem = callback

}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BullViewHolder(
LayoutInflater.from(parent.context).inflate(R.layout.activity_scanned_history,parent, false)

    )





    override fun onBindViewHolder(holder: BullViewHolder, position: Int){

val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener{ OnClickItem?.invoke(std)}

        holder.btnDelete.setOnClickListener{OnClickDeleteItem?.invoke(std)}

    }




    class BullViewHolder(var view: View): RecyclerView.ViewHolder(view) {
       private var bullcode = view.findViewById<TextView>(R.id.tvbullcode)
        private var datecode = view.findViewById<TextView>(R.id.tvdatecode)
 var btnDelete = view.findViewById<Button>(R.id.delete)


        fun bindView(std: BullModel){
bullcode.text = std.bullCode.toString()
datecode.inputType = std.dateCode


        }

    }

    override fun getItemCount(): Int {
        return stdList.size
    }

}