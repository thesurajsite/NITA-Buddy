package com.surajverma.nitahelpers


import SharedPreferences.SharedPreferencesManager
import android.content.Context
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.Objects

class myRequest_RecyclerAdapter(val context: Context,val arrMyRequest: ArrayList<myRequest_model>) : RecyclerView.Adapter<myRequest_RecyclerAdapter.ViewHolder>() {

    private lateinit var jsonObject: JSONObject
    private lateinit var SharedPreferencesManager: SharedPreferencesManager

    init {
        SharedPreferencesManager= SharedPreferencesManager(context)

    }

    fun <T> addtoRequestQueue(request: Request<T>){
        requestQueue.add(request)
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        // FROM RECYCLER VIEW LAYOUT
        val storeName=itemView.findViewById<TextView>(R.id.storeName)
        val orderId=itemView.findViewById<TextView>(R.id.orderId)
        val orderTime=itemView.findViewById<TextView>(R.id.orderTime)
        val orderDetails=itemView.findViewById<TextView>(R.id.orderDetails)
        val orderStatus=itemView.findViewById<TextView>(R.id.orderStatus)
        val storeImage=itemView.findViewById<ImageView>(R.id.storeImage)

        val myRequestLayout=itemView.findViewById<LinearLayout>(R.id.myRequestLayout)
        val tapLayout=itemView.findViewById<LinearLayout>(R.id.tapLayout)
        val generateOtp=itemView.findViewById<TextView>(R.id.generateOtp)
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
        holder.orderStatus.text="Status: "+arrMyRequest[position].orderstatus
        holder.storeImage.setImageResource(arrMyRequest[position].image)


        // VISIBILITY CONTROLS FOR PHONE
        var flag=0
        holder.myRequestLayout.setOnClickListener {
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

        if (arrMyRequest[position].orderstatus=="ACCEPTED"){
            holder.generateOtp.visibility=View.VISIBLE
        }
        else{
            holder.generateOtp.visibility=View.GONE
        }

        holder.generateOtp.setOnClickListener {
            holder.generateOtp.setText("Generating OTP...")

            val orderId=arrMyRequest[position].orderId

            jsonObject= JSONObject()
            jsonObject.put("orderId", orderId)
            val url = "https://gharaanah.onrender.com/engineering/generateotp"
            val request = object : JsonObjectRequest(
                Method.POST, url, jsonObject,
                { jsonData ->
                    val action = jsonData.getBoolean("status")
                    if(action){
                        val response = jsonData.getString("response") // Contains text "Your OTP is: "
                        val otp=jsonData.getLong("otp")
                        holder.generateOtp.setText("\"$otp\"")
                    }
                },
                {
                    Toast.makeText(context, "Some Error Occured", Toast.LENGTH_SHORT).show()
                    Log.w("otp-request", "${it.message}")
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    val token=SharedPreferencesManager.getUserToken()
                    headers["Authorization"] = "Bearer $token"
                    return headers
                }

            }

            addtoRequestQueue(request)
        }
    }
}
