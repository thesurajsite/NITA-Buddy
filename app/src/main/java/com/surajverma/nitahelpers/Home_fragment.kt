package com.surajverma.nitahelpers

import SharedPreferences.SharedPreferencesManager
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
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
import androidx.core.content.ContextCompat.getSystemService
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.surajverma.nitahelpers.databinding.ActivityMainBinding
import com.surajverma.nitahelpers.databinding.FragmentHomeBinding
import org.json.JSONObject


class Home_fragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var jsonObject: JSONObject
    private lateinit var SharedPreferencesManager: SharedPreferencesManager
    private lateinit var vibrator: Vibrator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        SharedPreferencesManager= SharedPreferencesManager(requireContext())
        vibrator=requireContext().getSystemService(VIBRATOR_SERVICE) as Vibrator

//        //API Call
//        jsonObject= JSONObject()
//        val url = "https://gharaanah.onrender.com/engineering/studentrequest"
//        val request = object : JsonObjectRequest(
//            Method.GET, url, jsonObject,
//            { jsonData ->
//                val action=jsonData.getBoolean("action")
//
//
//                Log.d("Profile-call", "$jsonData")
//                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
//            },
//            {
//                Toast.makeText(requireContext(), "Some Error Occured", Toast.LENGTH_SHORT).show()
//                Log.e("Profile-call", "Error: ${it.message}")
//            }
//        ) {
//            // Override getHeaders to add the Authorization header
//            override fun getHeaders(): MutableMap<String, String> {
//                val headers = HashMap<String, String>()
//                val token=SharedPreferencesManager.getUserToken()
//                headers["Authorization"] = "Bearer $token"
//                return headers
//            }
//        }
//
//        addtoRequestQueue(request)
//
//




        return binding.root
    }


}