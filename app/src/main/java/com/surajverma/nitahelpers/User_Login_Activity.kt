package com.surajverma.nitahelpers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import androidx.core.content.ContextCompat
import com.surajverma.nitahelpers.databinding.ActivityUserLoginBinding

class User_Login_Activity : AppCompatActivity() {
    lateinit var binding: ActivityUserLoginBinding
    private lateinit var vibrator: Vibrator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vibrator=getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        binding.LoginToSignupTv.setOnClickListener {
            vibrator.vibrate(50)
            startActivity(Intent(this, User_SignUp::class.java))
            finish()
        }

    }
}