package com.example.church_si.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.church_si.R
import com.example.church_si.databinding.CategoryListitemBinding

class CategoryAdapter(private val catList: ArrayList<String>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    // create new views
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryListitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
       holder.binding.catName.text = catList[position]
        Glide.with(context!!).load(R.drawable.jesus).into(holder.binding.catImg)
    }

    override fun getItemCount(): Int {
      return catList.size
    }



    class ViewHolder(var binding: CategoryListitemBinding) : RecyclerView.ViewHolder(binding.root)
}