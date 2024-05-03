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
class studentRequest_RecyclerAdapter(val context: Context,val arrStudentRequest: ArrayList<studentRequest_model>) : RecyclerView.Adapter<studentRequest_RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){



        // FROM RECYCLER VIEW LAYOUT
        val storeName=itemView.findViewById<TextView>(R.id.storeName)
        val orderId=itemView.findViewById<TextView>(R.id.orderId)
        val orderTime=itemView.findViewById<TextView>(R.id.orderTime)
        val orderDetails=itemView.findViewById<TextView>(R.id.orderDetails)
        val orderStatus=itemView.findViewById<TextView>(R.id.orderStatus)
        val storeImage=itemView.findViewById<ImageView>(R.id.storeImage)
        val phoneNo=itemView.findViewById<TextView>(R.id.phoneNo)

        val studentRequestLayout=itemView.findViewById<LinearLayout>(R.id.studentRequestLayout)
        val tapLayout=itemView.findViewById<LinearLayout>(R.id.tapLayout)


        val recyclerLayout=itemView.findViewById<LinearLayout>(R.id.myRequestLayout)
        //VIBRATOR VIBRATOR VIBRATOR
        val vibrator = itemView.context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.students_request_layout, parent, false))

    }

    override fun getItemCount(): Int {
        return arrStudentRequest.size


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.orderId.text=arrStudentRequest[position].orderId
        holder.orderTime.text=arrStudentRequest[position].orderTime
        holder.orderDetails.text=arrStudentRequest[position].orderDetails
        holder.storeName.text=arrStudentRequest[position].store
        holder.orderStatus.text=arrStudentRequest[position].orderstatus
        holder.phoneNo.text=arrStudentRequest[position].phoneNo
        holder.storeImage.setImageResource(arrStudentRequest[position].image)

        // VISIBILITY CONTROLS FOR PHONE
        var flag=0
        holder.studentRequestLayout.setOnClickListener {
            if(flag==0){
                flag=1
                holder.vibrator.vibrate(50)
                holder.tapLayout.visibility=View.VISIBLE
            }
            else{
                flag=0
                holder.vibrator.vibrate(50)
                holder.tapLayout.visibility=View.GONE
            }
        }

    }



}