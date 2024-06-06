package com.example.saurabhpracticle.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saurabhpracticle.data.UserListProfile
import com.example.saurabhpracticle.databinding.AdapterproductlistBinding

class UserListAdapter(val context: Context,val userListProfile: ArrayList<UserListProfile>):
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListAdapter.UserViewHolder {
        val binding = AdapterproductlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    inner class UserViewHolder(var binding:AdapterproductlistBinding): RecyclerView.ViewHolder(binding.root){}

    override fun getItemCount(): Int {
        return userListProfile.size
    }

    override fun onBindViewHolder(holder: UserListAdapter.UserViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(context)
                .load(userListProfile[position].avatar)
                .into(ivavatar)
            tvname.text=userListProfile[position].firstName
        }
    }
}