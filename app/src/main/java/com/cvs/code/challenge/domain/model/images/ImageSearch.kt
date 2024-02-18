package com.cvs.code.challenge.domain.model.images

data class ImageSearch(
    val description: String,
    val generator: String,
    val items: List<Item>,
    val link: String,
    val modified: String,
    val title: String
)