package com.planetpeopleplatform.greenforest.model

import com.google.gson.annotations.SerializedName

data class Item(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("categoryId")
    var categoryId: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("price")
    var price: Double? = null,

    @SerializedName("photos")
    var photos: List<String> = arrayListOf()
)