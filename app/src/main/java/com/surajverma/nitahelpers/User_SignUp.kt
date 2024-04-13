package com.surajverma.nitahelpers

import android.R as AndroidResources
import com.surajverma.nitahelpers.R
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.surajverma.nitahelpers.databinding.ActivityUserSignUpBinding
import com.surajverma.nitahelpers.databinding.EnterOtpLayoutBinding
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class User_SignUp : AppCompatActivity() {
    lateinit var binding: ActivityUserSignUpBinding
    private lateinit var apiInterface: SignUp_Interface
    private lateinit var jsonObject: JSONObject

    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(this.applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Spinner TextView
        spinner()

        binding.getOTPBtn.setOnClickListener {
            val name = binding.nameEt.text.toString()
            val year = binding.yearSpinner.selectedItem.toString()
            val phoneNo = binding.phoneEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val branch = binding.branchSpinner.selectedItem.toString()
            val hostel = binding.hostelSpinner.selectedItem.toString()
            val enrollmentNo = binding.enrollmentEt.text.toString()

            // Create SignUpData object
            val signUpData = SignUp_Data(
                name=name,
                year = year,
                phoneNo = phoneNo,
                password = password,
                branch = branch,
                hostel = hostel,
                enrollmentNo = enrollmentNo
            )

            signUpUsingVolley(signUpData)
        }

    }

    private fun signUpUsingVolley(request: SignUp_Data) {
        val url = "https://gharaanah.onrender.com/engineering/signup"
        jsonObject = JSONObject()
        jsonObject.put("name", request.name)
        jsonObject.put("phoneNo", request.phoneNo)
        jsonObject.put("branch", request.branch)
        jsonObject.put("year", request.year)
        jsonObject.put("password", request.password)
        jsonObject.put("enrollmentNo", request.enrollmentNo)
        jsonObject.put("hostel", request.hostel)



        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { jsonData ->
                val response = jsonData.getString("response")
                val action = jsonData.getBoolean("action")

                if(action==true){
                    otpverification()
                }

                Log.w("volley-call", "jsonData= $jsonData jsonObject= $jsonObject")
                Log.w("volley-call", "action: $action, response: $response")
                Toast.makeText(this@User_SignUp, response, Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(this@User_SignUp, it.message, Toast.LENGTH_SHORT).show()
                Log.w("volley-call", "${it.message}")
            }
        )

        addToRequestQueue(request)
    }

    private fun otpverification() {

        binding.enterOTPEt.visibility=View.VISIBLE
        binding.verifyOTPBtn.visibility=View.VISIBLE
        binding.getOTPBtn.visibility=View.GONE

        binding.verifyOTPBtn.setOnClickListener {
            val otp=binding.enterOTPEt.text.toString()

           if(otp!=""){
               jsonObject.put("otp", otp)

               val url = "https://gharaanah.onrender.com/engineering/otpverify"
               val request = JsonObjectRequest(
                   Request.Method.POST, url, jsonObject,
                   { jsonData ->
                       val response = jsonData.getString("response")
                       val action = jsonData.getBoolean("action")

                       Log.w("otp-verification", "jsonData= $jsonData jsonObject= $jsonObject")
                       Log.w("otp-verification", "action: $action, response: $response")
                       Toast.makeText(this@User_SignUp, response, Toast.LENGTH_SHORT).show()
                   },
                   {
                       Toast.makeText(this@User_SignUp, it.message, Toast.LENGTH_SHORT).show()
                       Log.w("otp-verification", "${it.message}")
                   }
               )

               addToRequestQueue(request)


           }
        }
    }

    private fun spinner() {

        // Hostel Spinner
        val list_of_hostels= arrayListOf<String>("Select Hostel","Aryabhatta", "RNT")
        val hostelSpinnerAdapter= ArrayAdapter(this, AndroidResources.layout.simple_spinner_item, list_of_hostels)
        hostelSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.hostelSpinner.adapter=hostelSpinnerAdapter

        //Branch Spinner
        val list_of_branch= arrayListOf<String>("Select Branch", "Computer Science", "Electronucs", "Mechanical", "Civil", "Electrical", "Chemical", "Production", "Instrumentation", "BioTech", "IIIT")
        val branchSpinnerAdapter= ArrayAdapter(this, AndroidResources.layout.simple_spinner_item, list_of_branch)
        branchSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.branchSpinner.adapter=branchSpinnerAdapter

        //Year Spinner
        val list_of_year= arrayListOf<String>("Select Year", "1st Year", "2nd Year", "3rd Year", "4th Year")
        val yearSpinnerAdapter= ArrayAdapter(this, AndroidResources.layout.simple_spinner_item, list_of_year)
        yearSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.yearSpinner.adapter=yearSpinnerAdapter

    }


}