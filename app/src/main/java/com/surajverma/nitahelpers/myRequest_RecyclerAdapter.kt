package com.surajverma.nitahelpers


import android.content.Context
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class myRequest_RecyclerAdapter(val context: Context,val arrMyRequest: ArrayList<myRequest_model>) : RecyclerView.Adapter<myRequest_RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){



        // FROM RECYCLER VIEW LAYOUT
        val storeName=itemView.findViewById<TextView>(R.id.storeName)
        val orderId=itemView.findViewById<TextView>(R.id.orderId)
        val orderTime=itemView.findViewById<TextView>(R.id.orderTime)
        val orderDetails=itemView.findViewById<TextView>(R.id.orderDetails)
        val orderStatus=itemView.findViewById<TextView>(R.id.orderStatus)
        val storeImage=itemView.findViewById<ImageView>(R.id.storeImage)


        val recyclerLayout=itemView.findViewById<LinearLayout>(R.id.myRequestLayout)
        //VIBRATOR VIBRATOR VIBRATOR
        val vibrator = itemView.context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.my_requests_layout, parent, false))

    }

    override fun getItemCount(): Int {
        return arrMyRequest.size


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.orderId.text=arrMyRequest[position].orderId
        holder.orderTime.text=arrMyRequest[position].orderTime
        holder.orderDetails.text=arrMyRequest[position].orderDetails
        holder.storeName.text=arrMyRequest[position].store
        holder.orderStatus.text=arrMyRequest[position].orderstatus
        holder.storeImage.setImageResource(arrMyRequest[position].image)


    }



}