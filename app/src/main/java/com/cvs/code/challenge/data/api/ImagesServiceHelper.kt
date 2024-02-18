package com.cvs.code.challenge.data.api

import com.cvs.code.challenge.data.model.response.images.ImagesSearchResponse

interface ImagesServiceHelper {

    suspend fun getImagesFromFlikr(tags:String): ImagesSearchResponse
}