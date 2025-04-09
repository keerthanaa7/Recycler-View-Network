package com.example.recycler_view_network_list

import com.google.gson.annotations.SerializedName


data class Picture(
    @SerializedName("id")
    val id: String,
    @SerializedName("download_url")
    val url:String
)
