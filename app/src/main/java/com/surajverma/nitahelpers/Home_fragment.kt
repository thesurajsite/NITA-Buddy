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
import androidx.recyclerview.widget.LinearLayoutManager
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
    private lateinit var arrStudentRequest:ArrayList<studentRequest_model>
    private lateinit var adapter: studentRequest_RecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        SharedPreferencesManager= SharedPreferencesManager(requireContext())
        vibrator=requireContext().getSystemService(VIBRATOR_SERVICE) as Vibrator

        studentRequestRecyclerView()



        return binding.root
    }

    private fun studentRequestRecyclerView() {
        arrStudentRequest= ArrayList()
        adapter= studentRequest_RecyclerAdapter(requireContext(), arrStudentRequest)
        binding.studentRequestRecyclerView.adapter=adapter
        binding.studentRequestRecyclerView.layoutManager=LinearLayoutManager(requireContext())

        arrStudentRequest.add(studentRequest_model(R.drawable.amazon,"GHIN12345","parcel", "Amazon", "12:36 | 18-04-24", "Placed", "This is an Order", "Gate 2", "7667484399"))
        arrStudentRequest.add(studentRequest_model(R.drawable.flipkart,"GHIN12345","parcel", "Flipkart", "12:36 | 18-04-24", "Placed", "This is an Order", "Gate 2", "7667484399"))
        arrStudentRequest.add(studentRequest_model(R.drawable.john,"GHIN12345","parcel", "John", "12:36 | 18-04-24", "Placed", "This is an Order", "Gate 2", "7667484399"))
        arrStudentRequest.add(studentRequest_model(R.drawable.wow,"GHIN12345","parcel", "Wow", "12:36 | 18-04-24", "Placed", "This is an Order", "Gate 2", "7667484399"))
        arrStudentRequest.add(studentRequest_model(R.drawable.amazon,"GHIN12345","parcel", "Amazon", "12:36 | 18-04-24", "Placed", "This is an Order", "Gate 2", "7667484399"))

    }


}