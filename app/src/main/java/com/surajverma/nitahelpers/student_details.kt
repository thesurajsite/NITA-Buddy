package com.surajverma.nitahelpers

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.surajverma.nitahelpers.databinding.ActivityStudentDetailsBinding

class student_details : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // THIS ACTIVITY CONTAINS  THE DETAILS OF THE STUDENTS WHO CREATED A REQUEST
        // WHEN SOMEONE CLICKS ON THE STUDENT NAME FROM THE "REQUESTS FOR YOU" SECTION, THEY WILL COME HERE

        val name= intent.getStringExtra("name")
        val branch= intent.getStringExtra("branch")
        val enrollmentNo= intent.getStringExtra("enrollmentNo")
        val year= intent.getStringExtra("year")
        val hostel= intent.getStringExtra("hostel")
        val phoneNo= intent.getStringExtra("phoneNo")

        binding.nameTv.text=name
        binding.branchTv.text=branch
        binding.enrollmentTv.text=enrollmentNo
        binding.yearTv.text=year
        binding.hostelTv.text=hostel
        binding.phoneTv.text=phoneNo

       // Toast.makeText(this, "$name, $branch, $year, $hostel, $phoneNo", Toast.LENGTH_SHORT).show()



    }
}