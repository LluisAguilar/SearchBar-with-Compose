package com.cvs.code.challenge.data.repository

import com.cvs.code.challenge.data.api.ImagesServiceHelper
import com.cvs.code.challenge.data.helpers.ImagesResponseToImages
import com.cvs.code.challenge.domain.Response
import com.cvs.code.challenge.domain.model.images.ImageSearch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(
    private val imagesServiceHelper: ImagesServiceHelper,
    private val dispatcher:CoroutineDispatcher
) : ImagesRepository {

    private val imagesResponseToImages = ImagesResponseToImages()
    override fun getImagesSearch(tags: String): Flow<Response<ImageSearch>>  = flow {
        emit(Response.Loading)

        emit(Response.Success(imagesResponseToImages.map(imagesServiceHelper.getImagesFromFlikr(tags))))
    }.catch { exception ->
        emit(Response.Error(exception))
    }.flowOn(dispatcher)

}