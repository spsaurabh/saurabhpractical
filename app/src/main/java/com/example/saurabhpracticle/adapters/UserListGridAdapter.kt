package com.example.saurabhpracticle.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saurabhpracticle.data.UserListProfile
import com.example.saurabhpracticle.databinding.AdaptergriduserlistBinding

class UserListGridAdapter(val context: Context, val userListProfile: ArrayList<UserListProfile>): RecyclerView.Adapter<UserListGridAdapter.UserGridViewHolder>() {
    inner class UserGridViewHolder(var binding: AdaptergriduserlistBinding) :RecyclerView.ViewHolder(binding.root){}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGridViewHolder {
        val binding = AdaptergriduserlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserGridViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return userListProfile.size
    }
    override fun onBindViewHolder(holder: UserGridViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(context)
                .load(userListProfile[position].avatar)
                .into(ivavatar)
            tvname.text=userListProfile[position].firstName
        }
    }
}