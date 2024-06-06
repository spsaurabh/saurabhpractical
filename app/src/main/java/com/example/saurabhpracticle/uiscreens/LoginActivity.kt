package com.example.saurabhpracticle.uiscreens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.saurabhpracticle.R
import com.example.saurabhpracticle.base.ApiClient
import com.example.saurabhpracticle.base.ApiUrl
import com.example.saurabhpracticle.base.BaseActivity
import com.example.saurabhpracticle.base.isNetworkAvailable
import com.example.saurabhpracticle.constants.Constants
import com.example.saurabhpracticle.databinding.ActivityLoginBinding
import com.example.saurabhpracticle.viewmodels.CommonViewModel

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var commonViewModel: CommonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initClickListeners()
        getApiResponse()
    }
    private fun getApiResponse() {  try {
        commonViewModel.loginToken.observe(this, Observer {
            if(!it.isNullOrEmpty()){
                if (it==Constants.USER_NOT_FOUND){
                    Toast.makeText(this,Constants.USER_NOT_FOUND,Toast.LENGTH_SHORT).show()
                }else{
                    toastMsg(this,Constants.LOGGEDIN,0)
                    startActivity(Intent(this,ProductListActivity::class.java))
                }
            }else{
                toastMsg(this,Constants.SOMETHING_WENT_WRONG,0)
            }
        })
    }catch(e:Exception){
        e.printStackTrace()
    }
    }
    private fun initClickListeners() {
        with(binding){
            btnLogin.setOnClickListener {
                if (binding.etEmail.text.isEmpty()){
                    toastMsg(this@LoginActivity,getString(R.string.enteremail),0)
                }else if (binding.etPassword.text.isEmpty()){
                    toastMsg(this@LoginActivity,getString(R.string.enterpass),0)
                }else{
                    if(isNetworkAvailable(this@LoginActivity)){
                        val hashMap = HashMap<String,String>()
                        val getEmail = binding.etEmail.text.toString()
                        val getPassword = binding.etPassword.text.toString()
                        hashMap["email"] = getEmail
                        hashMap["password"] = getPassword
                        commonViewModel.hitLoginPostAPI(ApiUrl.BASE_URL+ApiUrl.LOGIN,hashMap)
                    }else{
                        toastMsg(this@LoginActivity,getString(R.string.interconnection),0)
                    }
                }
            }
        }
    }
    private fun initViews() {   try {
        commonViewModel = ViewModelProvider(this)[CommonViewModel::class.java]
        onBackPressedDispatcher.addCallback(this,object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }catch(e:Exception){
        e.printStackTrace()
    }
    }
}