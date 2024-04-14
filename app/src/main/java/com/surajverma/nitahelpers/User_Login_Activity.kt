package com.surajverma.nitahelpers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.surajverma.nitahelpers.databinding.ActivityUserLoginBinding
import org.json.JSONObject

class User_Login_Activity : AppCompatActivity() {
    lateinit var binding: ActivityUserLoginBinding
    private lateinit var vibrator: Vibrator
    private lateinit var jsonObject: JSONObject

    fun <T> addtoRequestQueue(request: Request<T>){
        requestQueue.add(request)
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(this.applicationContext)
    }
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

        binding.loginBtn.setOnClickListener {
            vibrator.vibrate(50)
            binding.Progressbar.visibility= View.VISIBLE

            val phoneNo=binding.phoneEt.text.toString()
            val password= binding.passwordEt.text.toString()

            jsonObject= JSONObject()
            jsonObject.put("phoneNo", phoneNo)
            jsonObject.put("password", password)

            val url = "https://gharaanah.onrender.com/engineering/login"
            val request= JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                { jsonData->
                    binding.Progressbar.visibility= View.INVISIBLE
                    val action=jsonData.getBoolean("action")
                    val response=jsonData.getString("response")

                    if(action==true){
                        val token=jsonData.getString("token")
                        Log.w("login-response", "jsonData= $jsonData jsonObject= $jsonObject")
                        Log.d("login-response", "action= $action ; response= $response ; token= $token")
                    }

                    Toast.makeText(this, response, Toast.LENGTH_SHORT).show()

                },{
                    binding.Progressbar.visibility= View.INVISIBLE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    Log.w("login-response", "${it.message}")
                }
            )

            addtoRequestQueue(request)

        }

    }
}