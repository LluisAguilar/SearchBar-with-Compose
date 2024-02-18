package com.cvs.code.challenge.ui.state

import com.cvs.code.challenge.domain.model.images.ImageSearch

sealed class ImageSearchState {

    object Loading : ImageSearchState()

    data class SuccessState(val imagesSearch : ImageSearch) : ImageSearchState()

    data class ErrorState(val error:String) : ImageSearchState()

}

