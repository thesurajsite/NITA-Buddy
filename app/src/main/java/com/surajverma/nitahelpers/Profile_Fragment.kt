package com.surajverma.nitahelpers

import SharedPreferences.SharedPreferencesManager
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.surajverma.nitahelpers.databinding.FragmentHomeBinding
import com.surajverma.nitahelpers.databinding.FragmentProfileBinding
import org.json.JSONObject

class Profile_Fragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    private lateinit var jsonObject: JSONObject
    private lateinit var SharedPreferencesManager: SharedPreferencesManager
    private lateinit var vibrator: Vibrator
    fun <T> addtoRequestQueue(request: Request<T>){
        requestQueue.add(request)
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        SharedPreferencesManager= SharedPreferencesManager(requireContext())
        vibrator=requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        binding.Progressbar.visibility=View.VISIBLE

        fetchProfileData()
        fetchMyRequests()




        binding.logoutBtn.setOnClickListener{
            logout()
        }


        return binding.root
    }

    private fun fetchMyRequests() {

        //API Call
        jsonObject= JSONObject()
        val url = "https://gharaanah.onrender.com/engineering/studentrequest"
        val request = object : JsonObjectRequest(
            Method.GET, url, jsonObject,
            { jsonData ->
                val action=jsonData.getBoolean("action")


                Log.d("my-requests", "$jsonData")
                //Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(requireContext(), "Some Error Occured", Toast.LENGTH_SHORT).show()
                Log.e("my-requests", "Error: ${it.message}")
            }
        ) {
            // Override getHeaders to add the Authorization header
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                val token=SharedPreferencesManager.getUserToken()
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }

        addtoRequestQueue(request)

    }

    private fun fetchProfileData() {

        //API CALLING
        jsonObject= JSONObject()
        jsonObject.put("token", SharedPreferencesManager.getUserToken())
        val url = "https://gharaanah.onrender.com/engineering/profile"
        val request = object : JsonObjectRequest(
            Method.GET, url, jsonObject,
            { jsonData ->
                binding.Progressbar.visibility=View.INVISIBLE
                val action=jsonData.getBoolean("action")
                if(action==true){
                    val studentObject=jsonData.getJSONObject("student")
                    val name=studentObject.getString("name")
                    val enrollmentNo=studentObject.getString("enrollmentNo")
                    val branch=studentObject.getString("branch")
                    val year=studentObject.getString("year")
                    val hostel=studentObject.getString("hostel")
                    val phoneNo=studentObject.getString("phoneNo")

                    binding.nameTv.setText(name)
                    binding.enrollmentTv.setText("Er No: $enrollmentNo")
                    binding.branchTv.setText(branch)
                    binding.yearTv.setText(year)
                    binding.hostelTv.setText("Hostel: $hostel")
                    binding.phoneTv.setText("Phone No: $phoneNo")

                }

                Log.d("Profile-call", "$jsonData")
//                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            },
            {
                binding.Progressbar.visibility=View.INVISIBLE
                Toast.makeText(requireContext(), "Some Error Occured", Toast.LENGTH_SHORT).show()
                Log.e("Profile-call", "Error: ${it.message}")
            }
        ) {
            // Override getHeaders to add the Authorization header
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                val token=SharedPreferencesManager.getUserToken()
                headers["Authorization"] = "Bearer $token"
                return headers
            }
        }

        addtoRequestQueue(request)

    }

    private fun logout() {
        binding.logoutBtn.setOnClickListener {
            vibrator.vibrate(50)

            SharedPreferencesManager.updateLoginState(false)
            SharedPreferencesManager.updateUserToken("")
            Toast.makeText(requireContext(), "Logged Out Successfully", Toast.LENGTH_SHORT).show()

            startActivity(Intent(requireContext(), User_Login_Activity::class.java))
            (activity as AppCompatActivity).finish()
        }
    }
}
