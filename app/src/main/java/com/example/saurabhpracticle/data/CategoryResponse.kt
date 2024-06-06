package com.example.saurabhpracticle.data

import com.google.gson.annotations.SerializedName

class CategoryResponse {
    @SerializedName("page")
    var page: Int?= null
    @SerializedName("per_page")
    var perPage: Int?= null
    @SerializedName("total")
    var total: Int? = null
    @SerializedName("total_pages")
    var totalPages:Int?= null
    @SerializedName("data")
    var data: ArrayList<CategoryProfile> = arrayListOf()
    @SerializedName("support")
    var support: Support? = Support()
}