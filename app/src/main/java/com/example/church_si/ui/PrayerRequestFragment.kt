package com.example.church_si.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.church_si.R
import com.example.church_si.databinding.FragmentContactUsBinding
import com.example.church_si.databinding.FragmentPrayerRequestBinding
import com.example.church_si.databinding.FragmentVideosBinding


class PrayerRequestFragment : Fragment() {
    private lateinit var binding: FragmentPrayerRequestBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrayerRequestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

}