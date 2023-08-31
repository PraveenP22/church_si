package com.example.church_si.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.church_si.R
import com.example.church_si.adapter.DailyPromisesAdapter
import com.example.church_si.adapter.ShortVideoAdapter
import com.example.church_si.adapter.YTVideoAdapter
import com.example.church_si.databinding.FragmentContactUsBinding
import com.example.church_si.databinding.FragmentVideosBinding


class VideosFragment : Fragment() {

    private lateinit var binding:FragmentVideosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.ytvideoRcview.adapter = YTVideoAdapter()
        binding.shortvideoRcview.adapter = ShortVideoAdapter()

        return root
    }

}