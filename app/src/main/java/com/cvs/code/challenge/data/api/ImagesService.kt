package com.android.code.challenge.justo.data.retrofit

import com.cvs.code.challenge.data.model.response.images.ImagesSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesService {

    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun getImagesFromFlikr(
        @Query("tags") tags:String
    ): ImagesSearchResponse
}