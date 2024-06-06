package com.example.saurabhpracticle.data

import com.google.gson.annotations.SerializedName

class CategoryProfile {
    @SerializedName("id")
    var id : Int? = null
    @SerializedName("name")
    var name : String? = null
    @SerializedName("year")
    var year: Int? = null
    @SerializedName("color")
    var color: String? = null
    @SerializedName("pantone_value")
    var pantoneValue : String? = null
}