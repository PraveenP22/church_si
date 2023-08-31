package com.example.church_si.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.church_si.R
import com.example.church_si.databinding.FragmentAboutBinding
import com.example.church_si.databinding.FragmentHomeBinding

class AboutFragment : Fragment() {

    private lateinit var binding:FragmentAboutBinding;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

}