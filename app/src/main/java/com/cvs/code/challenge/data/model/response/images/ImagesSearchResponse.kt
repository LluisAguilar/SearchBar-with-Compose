package com.cvs.code.challenge.data.model.response.images

data class ImagesSearchResponse(
    val description: String,
    val generator: String,
    val items: List<Item>,
    val link: String,
    val modified: String,
    val title: String
)