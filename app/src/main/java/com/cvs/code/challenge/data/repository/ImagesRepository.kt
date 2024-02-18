package com.cvs.code.challenge.data.repository

import com.cvs.code.challenge.domain.Response
import com.cvs.code.challenge.domain.model.images.ImageSearch
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {

    fun getImagesSearch(tags:String): Flow<Response<ImageSearch>>
}