package com.cvs.code.challenge.data.helpers

import com.cvs.code.challenge.core.mapper.Mapper
import com.cvs.code.challenge.data.model.response.images.ImagesSearchResponse
import com.cvs.code.challenge.domain.model.images.ImageSearch
import com.cvs.code.challenge.domain.model.images.Item
import com.cvs.code.challenge.domain.model.images.Media

class ImagesResponseToImages : Mapper<ImagesSearchResponse, ImageSearch>() {
    override fun map(value: ImagesSearchResponse): ImageSearch {
        val imageSearch = ImageSearch(
            description = value.description,
            generator = value.generator,
            link = value.link,
            modified = value.modified,
            title = value.title,
            items =
                value.items.map {
                    Item(it.author,it.author_id,it.date_taken,it.description,it.link,
                        Media(m = it.media.m), it.published,it.tags,it.title)
                }
        )
        return imageSearch
    }

    override fun reverseMap(value: ImageSearch): ImagesSearchResponse {
        val imageSearch = ImagesSearchResponse(
            description = value.description,
            generator = value.generator,
            link = value.link,
            modified = value.modified,
            title = value.title,
            items =
            value.items.map {
                com.cvs.code.challenge.data.model.response.images.Item(it.author,it.author_id,it.date_taken,it.description,it.link,
                    com.cvs.code.challenge.data.model.response.images.Media(m = it.media.m), it.published,it.tags,it.title)
            }
        )
        return imageSearch
    }
}