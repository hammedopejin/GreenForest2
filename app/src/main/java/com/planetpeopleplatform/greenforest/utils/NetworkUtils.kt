package com.planetpeopleplatform.greenforest.utils

import android.content.Context
import android.net.ConnectivityManager

fun hasConnection(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.getActiveNetworkInfo()
    if (activeNetwork != null)
    { // connected to the internet
        if (activeNetwork.getType() === ConnectivityManager.TYPE_WIFI)
        {
            // connected to wifi
            return true
        }
        else if (activeNetwork.getType() === ConnectivityManager.TYPE_MOBILE)
        {
            // connected to the mobile provider's data plan
            return true
        }
    }
    else
        return false
    return false
}