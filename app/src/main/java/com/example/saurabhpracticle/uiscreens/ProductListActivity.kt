package com.example.saurabhpracticle.uiscreens

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saurabhpracticle.adapters.CategoryListAdapter
import com.example.saurabhpracticle.adapters.UserListAdapter
import com.example.saurabhpracticle.adapters.UserListGridAdapter
import com.example.saurabhpracticle.base.ApiUrl
import com.example.saurabhpracticle.base.BaseActivity
import com.example.saurabhpracticle.data.CategoryProfile
import com.example.saurabhpracticle.data.UserListProfile
import com.example.saurabhpracticle.databinding.ActivityProductListBinding
import com.example.saurabhpracticle.viewmodels.CommonViewModel

class ProductListActivity : BaseActivity() {
    private lateinit var binding: ActivityProductListBinding
    private lateinit var commonViewModel: CommonViewModel
    private lateinit var adapter: CategoryListAdapter
    private lateinit var gridAdapter: UserListGridAdapter
    private lateinit var listAdapter: UserListAdapter
    private lateinit var categoryProfile: ArrayList<CategoryProfile>
    private lateinit var userListProfile: ArrayList<UserListProfile>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initRv()
        initClickListeners()
        getApiResponce()

    }
    private fun initRv() {
        binding.rvcategory.layoutManager =  LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        adapter = CategoryListAdapter(this,categoryProfile)

        binding.rvVerticalUserList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        listAdapter = UserListAdapter(this,userListProfile)

        binding.rvGridUserList.layoutManager = GridLayoutManager(this,2)
        gridAdapter = UserListGridAdapter(this,userListProfile)
    }
    private fun getApiResponce() {  try {
        commonViewModel.catoryListReponse.observe(this, Observer {
            categoryProfile.addAll(it.data)
            binding.rvcategory.adapter = adapter
        })
        commonViewModel.userListResponse.observe(this, Observer {
            userListProfile.addAll(it.data)
            binding.rvVerticalUserList.adapter = listAdapter
            binding.rvGridUserList.adapter = gridAdapter
        })
    }catch(e:Exception){
        e.printStackTrace()
    }
    }

    private fun initClickListeners() {  try {
        with(binding){
            ivlist.setOnClickListener {
                ivlist.visibility = View.GONE
                ivGrid.visibility = View.VISIBLE
                rvVerticalUserList.visibility = View.VISIBLE
                rvGridUserList.visibility = View.GONE
            }
            ivGrid.setOnClickListener {
                ivlist.visibility = View.VISIBLE
                ivGrid.visibility = View.GONE
                rvGridUserList.visibility = View.VISIBLE
                rvVerticalUserList.visibility = View.GONE
            }
        }
    }catch(e:Exception){
        e.printStackTrace()
    }
    }
    private fun initViews() {   try {
        onBackPressedDispatcher.addCallback(this,object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
        categoryProfile = ArrayList()
        userListProfile = ArrayList()
        commonViewModel = ViewModelProvider(this)[CommonViewModel::class.java]
        commonViewModel.hitGetCategoryAPI(ApiUrl.BASE_URL+ApiUrl.UNKNOWN)
        commonViewModel.hitGetUserListApi(ApiUrl.USERS)
        binding.rvcategory.adapter = adapter
    }catch(e:Exception){
        e.printStackTrace()
    }
    }
}