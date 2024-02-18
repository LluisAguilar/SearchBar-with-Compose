package com.cvs.code.challenge.data.api

import com.android.code.challenge.justo.data.retrofit.ImagesService
import com.cvs.code.challenge.data.model.response.images.ImagesSearchResponse
import javax.inject.Inject

class ImagesServiceImpl @Inject constructor(
    private val imagesService: ImagesService
): ImagesServiceHelper {
    override suspend fun getImagesFromFlikr(tags: String): ImagesSearchResponse = imagesService.getImagesFromFlikr(tags)
}