package com.example.saurabhpracticle.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saurabhpracticle.base.ApiClient
import com.example.saurabhpracticle.constants.Constants
import com.example.saurabhpracticle.data.CategoryResponse
import com.example.saurabhpracticle.data.UserListResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommonViewModel() : ViewModel() {
    var loginToken = MutableLiveData<String>()
    var catoryListReponse = MutableLiveData<CategoryResponse>()
    var userListResponse = MutableLiveData<UserListResponse>()
    fun hitLoginPostAPI(url:String,map: HashMap<String,String>){
        ApiClient.getApiService()?.hitPostApiWithJsonParams(url,map)?.enqueue(
            object : Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code()==200){
                        Log.e("CommonViewModel","Token:${response.body().toString()}")
                        loginToken.value=response.body().toString()
                    }else if (response.code()==400){
                        loginToken.value=Constants.USER_NOT_FOUND
                    }
                    else{
                        loginToken.value=""
                    }
                }
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e("CommonViewModel","${t.message.toString()}")
                    loginToken.value=""
                }
            }
        )
    }
    fun hitGetCategoryAPI(url: String){
        ApiClient.getApiService()?.hitGetApiWithoutJsonParams(url)?.enqueue(
            object : Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if(response.code()==200){
                        val objectResponse = Gson().fromJson(Gson().toJson(response.body()),CategoryResponse::class.java)
                        catoryListReponse.value=objectResponse
                    }
                }
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }
            }
        )
    }
    fun hitGetUserListApi(url: String){
        ApiClient.getApiService()?.hitGetApiWithoutJsonParams(url)?.enqueue(
            object : Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if(response.code()==200){
                        val objectResponse = Gson().fromJson(Gson().toJson(response.body()),UserListResponse::class.java)
                        userListResponse.value=objectResponse
                        Log.e("CommonViewModel","UserListResponse"+response.body().toString())
                    }
                }
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }

            }
        )
    }
}