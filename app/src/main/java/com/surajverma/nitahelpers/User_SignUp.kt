package com.surajverma.nitahelpers

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.surajverma.nitahelpers.databinding.ActivityUserSignUpBinding
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

//        // Initialize Retrofit
//        val retrofit: Retrofit by lazy {
//            Retrofit.Builder()
//                .baseUrl("https://gharaanah.onrender.com/engineering/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }

        // Create the API interface implementation
//        apiInterface = retrofit.create(SignUp_Interface::class.java)

        binding.signupBtn.setOnClickListener {
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

    private suspend fun signup() {

//        //Getting User Input
//        val name = binding.nameEt.text.toString()
//        val year = binding.yearSpinner.selectedItem.toString()
//        val phoneNo = binding.phoneEt.text.toString()
//        val password = binding.passwordEt.text.toString()
//        val branch = binding.branchSpinner.selectedItem.toString()
//        val hostel = binding.hostelSpinner.selectedItem.toString()
//        val enrollmentNo = binding.enrollmentEt.text.toString()

        // Create SignUpData object
//        val signUpData = SignUp_Data(name, year, phoneNo, password, branch, hostel, enrollmentNo)
//        val call = apiInterface.signup(signUpData)
//
//        try{
//            call.enqueue(object: Callback<SignUpResponse>{
//                override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
//                    Log.w("api-call", response.toString())
//                    val body = response.body()
//                    //
//                    Log.w("api-call", (body).toString())
//                }
//
//
//                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
//                    //nothing
//                }
//
//            })
//        }
//        catch(e: Exception){
//            Toast.makeText(this@User_SignUp, e.message, Toast.LENGTH_LONG).show();
//        }

//        val response=apiInterface.signup(signUpData)
//        if(response.isSuccessful){
//            Toast.makeText(this@User_SignUp, "Signed up successfully", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(this@User_SignUp, "errorrrrrrrrrr", Toast.LENGTH_SHORT).show()
//        }

    }

    private fun signUpUsingVolley(request: SignUp_Data) {
        val url = "https://gharaanah.onrender.com/engineering/signup"
        val jsonObject = JSONObject()
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
                val action = jsonData.getBoolean("action")
                val response = jsonData.getString("response")

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

    private fun spinner() {

        // Hostel Spinner
        val list_of_hostels= arrayListOf<String>("Select Hostel","Aryabhatta", "RNT")
        val hostelSpinnerAdapter= ArrayAdapter(this, R.layout.simple_spinner_item, list_of_hostels)
        hostelSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.hostelSpinner.adapter=hostelSpinnerAdapter

        //Branch Spinner
        val list_of_branch= arrayListOf<String>("Select Branch", "Computer Science", "Electronucs", "Mechanical", "Civil", "Electrical", "Chemical", "Production", "Instrumentation", "BioTech", "IIIT")
        val branchSpinnerAdapter= ArrayAdapter(this, R.layout.simple_spinner_item, list_of_branch)
        branchSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.branchSpinner.adapter=branchSpinnerAdapter

        //Year Spinner
        val list_of_year= arrayListOf<String>("Select Year", "1st Year", "2nd Year", "3rd Year", "4th Year")
        val yearSpinnerAdapter= ArrayAdapter(this, R.layout.simple_spinner_item, list_of_year)
        yearSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.yearSpinner.adapter=yearSpinnerAdapter

    }


}