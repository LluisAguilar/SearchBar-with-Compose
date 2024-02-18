package com.cvs.code.challenge.domain.usecase

import com.cvs.code.challenge.data.repository.ImagesRepositoryImpl
import com.cvs.code.challenge.domain.Response
import com.cvs.code.challenge.domain.model.images.ImageSearch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImageSearchUseCase @Inject constructor(private val imagesRepository: ImagesRepositoryImpl){

    operator fun invoke(tags:String): Flow<Response<ImageSearch>> =
        imagesRepository.getImagesSearch(tags)

}