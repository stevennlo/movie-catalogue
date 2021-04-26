package com.example.moviecatalogue.repository

import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.moviecatalogue.graphql.MediaQuery
import com.example.moviecatalogue.graphql.MediasQuery
import com.example.moviecatalogue.graphql.type.MediaFormat
import com.example.moviecatalogue.graphql.type.MediaSeason
import com.example.moviecatalogue.service.AniListApiService
import com.example.moviecatalogue.service.Status

class MediaRepository {
    suspend fun getMedias(
        seasonYear: Int,
        season: MediaSeason,
        format: MediaFormat,
    ): Status<List<MediasQuery.Medium>> {
        return try {
            val result =
                AniListApiService.getInstance().query(MediasQuery(season, seasonYear, format))
                    .await()
            Status.success(result.data?.page?.media?.filterNotNull())
        } catch (e: ApolloException) {
            Status.error(null, null)
        }
    }

    suspend fun getMedia(mediaId: Int): Status<MediaQuery.Media> {
        return try {
            val result = AniListApiService.getInstance().query(MediaQuery(mediaId)).await()
            Status.success(result.data?.media)
        } catch (e: ApolloException) {
            Status.error(null, null)
        }
    }

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