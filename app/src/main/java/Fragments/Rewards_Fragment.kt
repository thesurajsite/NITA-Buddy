package Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.surajverma.nitahelpers.R
import com.surajverma.nitahelpers.databinding.FragmentHomeBinding
import com.surajverma.nitahelpers.databinding.FragmentRewardsBinding

class Rewards_Fragment : Fragment() {
    lateinit var binding: FragmentRewardsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRewardsBinding.inflate(inflater, container, false)







        return binding.root
    }

}