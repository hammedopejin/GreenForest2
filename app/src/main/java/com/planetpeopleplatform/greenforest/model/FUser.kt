package com.planetpeopleplatform.greenforest.model

import com.google.gson.annotations.SerializedName

data class FUser(

    @SerializedName("objectId")
    var objectId: String,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("firstName")
    var firstName: String? = null,

    @SerializedName("lastName")
    var lastName: String? = null,

    @SerializedName("fullName")
    var fullName: String? = null,

    @SerializedName("fullAddress")
    var fullAddress: String? = null,

    @SerializedName("onBoard")
    var onBoard: Boolean = false,

    @SerializedName("purchasedItemIds")
    var purchasedItemIds: List<String> = arrayListOf()
)