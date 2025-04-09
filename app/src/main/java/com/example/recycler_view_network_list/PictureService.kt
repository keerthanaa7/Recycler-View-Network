package com.example.recycler_view_network_list

import retrofit2.http.GET
import com.example.recycler_view_network_list.Picture
import retrofit2.http.Query

interface PictureService {

    @GET("list")
    suspend fun pictures(
        @Query("page") page:Int =1,
        @Query("limit") per_page :Int = PER_PAGE_COUNT
    ):List<Picture>

    companion object{
        const val PER_PAGE_COUNT = 30
        const val ROOT_URL = "https://picsum.photos/v2/"
    }
}