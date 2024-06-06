package com.example.saurabhpracticle.base

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
 @POST
 fun hitPostApiWithJsonParams(
     @Url url: String,
     @Body parameters : HashMap<String,String>
 ):Call<JsonObject>

 @GET
 fun hitGetApiWithoutJsonParams(@Url url:String):Call<JsonObject>
}