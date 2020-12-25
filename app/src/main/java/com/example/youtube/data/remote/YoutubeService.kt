package com.example.youtube.data.remote

import com.example.youtube.data.model.MainResponseYoutube
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {
    @GET("videos")
   suspend fun getPopularVideos(
        @Query("chart") chart: String
    ) : MainResponseYoutube
}