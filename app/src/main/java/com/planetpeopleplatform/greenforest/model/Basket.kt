package com.planetpeopleplatform.greenforest.model

import com.google.gson.annotations.SerializedName

data class Basket(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("ownerId")
    var ownerId: String? = null,

    @SerializedName("itemIds")
    var itemIds: List<String> = arrayListOf()
)