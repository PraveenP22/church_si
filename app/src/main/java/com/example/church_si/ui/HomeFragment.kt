package com.example.church_si.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.church_si.R
import com.example.church_si.adapter.CategoryAdapter
import com.example.church_si.adapter.ImageSlideAdapter
import com.example.church_si.adapter.VideosAdapter
import com.example.church_si.databinding.FragmentHomeBinding
import com.example.church_si.utils.CommonFunctions
import com.example.church_si.viewModel.HomeViewModel
import me.relex.circleindicator.CircleIndicator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var imagesList = ArrayList<Int>()
    private var catList = ArrayList<String>()
    private lateinit var viewPagerAdapter:ImageSlideAdapter
    private  var viewpager:ViewPager?=null
    private  var indicator:CircleIndicator?=null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        imagesList.clear()
        catList.clear()
        imagesList.add(R.drawable.church1)
        imagesList.add(R.drawable.church2)
        catList.add("Daily Promise Verse")
        catList.add("Sermons")
        catList.add("About Church")
        catList.add("Prayer Request")
        catList.add("Offering")
        catList.add("Upcoming Program")
        var mNoOfColumns = CommonFunctions.calculateNoOfColumns(requireActivity())
        val mGridLayoutManager =  GridLayoutManager(requireActivity(), mNoOfColumns);
        binding.cateRc.layoutManager = mGridLayoutManager
        binding.cateRc.adapter = CategoryAdapter(catList)
        binding.videosRc.adapter = VideosAdapter()


        imagesList.let{
            viewPagerAdapter = ImageSlideAdapter(requireContext(), it)
            binding.viewpager.adapter = viewPagerAdapter
            binding.indicator?.setViewPager(viewpager)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}