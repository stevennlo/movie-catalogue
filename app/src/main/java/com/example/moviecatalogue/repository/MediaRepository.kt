package com.example.moviecatalogue.repository

import com.example.moviecatalogue.graphql.MediaQuery
import com.example.moviecatalogue.graphql.MediasQuery
import com.example.moviecatalogue.graphql.type.MediaFormat
import com.example.moviecatalogue.graphql.type.MediaSeason
import com.example.moviecatalogue.wrapper.Status

interface MediaRepository {
    suspend fun getMedias(
        seasonYear: Int,
        season: MediaSeason,
        format: MediaFormat,
    ): Status<List<MediasQuery.Medium>>

    suspend fun getMedia(mediaId: Int): Status<MediaQuery.Media>

    companion object {
        fun getMediaSeason(month: Int): MediaSeason {
            val mediaSeasons =
                listOf(MediaSeason.WINTER,
                    MediaSeason.SPRING,
                    MediaSeason.SUMMER,
                    MediaSeason.FALL)
            return mediaSeasons[(month % 12) / 3]
        }
    }
}