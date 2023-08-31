package com.example.church_si.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.church_si.R
import com.example.church_si.adapter.DailyPromisesAdapter
import com.example.church_si.adapter.VideosAdapter
import com.example.church_si.databinding.FragmentContactUsBinding
import com.example.church_si.databinding.FragmentDailyPromisesBinding


class DailyPromisesFragment : Fragment() {

    private lateinit var binding:FragmentDailyPromisesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyPromisesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.ytvideoRcview.adapter = DailyPromisesAdapter()


        return root
    }

}