package com.example.saurabhpracticle.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.saurabhpracticle.data.CategoryProfile
import com.example.saurabhpracticle.databinding.AdaptercatogorylistBinding

class CategoryListAdapter(val context: Context,val profileList:ArrayList<CategoryProfile>): RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(val binding: AdaptercatogorylistBinding) : RecyclerView.ViewHolder(binding.root){}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = AdaptercatogorylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return profileList.size
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder.binding){
            tvCatName.text=profileList[position].name
            llView.setBackgroundColor(android.graphics.Color.parseColor(profileList[position].color))
        }
    }
}