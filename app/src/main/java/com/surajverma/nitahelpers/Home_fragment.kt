package com.surajverma.nitahelpers

import SharedPreferences.SharedPreferencesManager
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.surajverma.nitahelpers.databinding.ActivityMainBinding
import com.surajverma.nitahelpers.databinding.FragmentHomeBinding


class Home_fragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
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

//        binding.logoutBtn.setOnClickListener {
//            vibrator.vibrate(50)
//
//            SharedPreferencesManager.updateLoginState(false)
//            SharedPreferencesManager.updateUserToken("")
//            Toast.makeText(requireContext(), "Logged Out Successfully", Toast.LENGTH_SHORT).show()
//
//            startActivity(Intent(requireContext(), User_Login_Activity::class.java))
//            (activity as AppCompatActivity).finish()
//        }






        return binding.root
    }


}