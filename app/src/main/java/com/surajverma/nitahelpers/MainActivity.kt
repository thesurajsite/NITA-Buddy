package com.surajverma.nitahelpers

import SharedPreferences.SharedPreferencesManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.surajverma.nitahelpers.databinding.ActivityMainBinding
import com.surajverma.nitahelpers.databinding.ActivityUserLoginBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var SharedPreferencesManager: SharedPreferencesManager
    private lateinit var vibrator: Vibrator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SharedPreferencesManager= SharedPreferencesManager(this)
        vibrator=getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        //If User Not Logged In, Send it to Login Activity
        if(SharedPreferencesManager.getLoginState()==false){
            startActivity(Intent(this, User_Login_Activity::class.java))
            finish()
        }

        binding.logoutBtn.setOnClickListener {
            vibrator.vibrate(50)

            SharedPreferencesManager.updateLoginState(false)
            SharedPreferencesManager.updateUserToken("")
            Toast.makeText(this, "Logged Out Successfully", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, User_Login_Activity::class.java))
            finish()
        }

        //Default Fragment on StartUp
        replaceFragment(Home_fragment())
        binding.bottomNavigation.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> replaceFragment(Home_fragment())
                R.id.profile -> replaceFragment(Profile_Fragment())
            }

            return@setOnItemSelectedListener true
        }





    }

   private fun replaceFragment(fragment: Fragment){
       val fragmentManager=supportFragmentManager
       val fragmentTransaction=fragmentManager.beginTransaction()
       fragmentTransaction.replace(R.id.frameLayout, fragment)
       fragmentTransaction.commit()
       vibrator.vibrate(50)
   }
}