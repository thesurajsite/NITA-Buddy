package com.surajverma.nitahelpers

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.surajverma.nitahelpers.databinding.ActivityUserSignUpBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Objects


class User_SignUp : AppCompatActivity() {
    lateinit var binding: ActivityUserSignUpBinding
    private lateinit var apiInterface: SignUp_Interface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Spinner TextView
        spinner()

        // Initialize Retrofit
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://gharaanah.onrender.com/engineering/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        // Create the API interface implementation
        apiInterface = retrofit.create(SignUp_Interface::class.java)

        binding.signupBtn.setOnClickListener {
            lifecycleScope.launch {
                signup()
            }
        }

    }

    private suspend fun signup() {

        //Getting User Input
        val name = binding.nameEt.text.toString()
        val year = binding.yearSpinner.selectedItem.toString()
        val phoneNo = binding.phoneEt.text.toString()
        val password = binding.passwordEt.text.toString()
        val branch = binding.branchSpinner.selectedItem.toString()
        val hostel = binding.hostelSpinner.selectedItem.toString()
        val enrollmentNo = binding.enrollmentEt.text.toString()

        // Create SignUpData object
        val signUpData = SignUp_Data(name, year, phoneNo, password, branch, hostel, enrollmentNo)

        // Call your API here
        // Call your API here
        val call: Call<SignUp_Data> = apiInterface.signup(signUpData)

        call.enqueue(object : Callback<SignUp_Data> {
            override fun onResponse(call: Call<SignUp_Data>, response: Response<SignUp_Data>) {
                // Handle success response
                if (response.isSuccessful) {
                    Toast.makeText(this@User_SignUp, "Signed up successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@User_SignUp, "Couldn't Signup", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SignUp_Data>, t: Throwable) {
                Toast.makeText(this@User_SignUp, "Some Error Occurred", Toast.LENGTH_SHORT).show()
            }
        })


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

