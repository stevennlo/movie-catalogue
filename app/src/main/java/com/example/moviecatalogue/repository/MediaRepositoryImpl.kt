package com.example.moviecatalogue.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.moviecatalogue.graphql.MediaQuery
import com.example.moviecatalogue.graphql.MediasQuery
import com.example.moviecatalogue.graphql.type.MediaFormat
import com.example.moviecatalogue.graphql.type.MediaSeason
import com.example.moviecatalogue.wrapper.Status
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(private val apiService: ApolloClient) :
    MediaRepository {
    override suspend fun getMedias(
        seasonYear: Int,
        season: MediaSeason,
        format: MediaFormat,
    ): Status<List<MediasQuery.Medium>> {
        return try {
            val result =
                apiService.query(MediasQuery(season, seasonYear, format))
                    .await()
            Status.success(result.data?.page?.media?.filterNotNull())
        } catch (e: ApolloException) {
            Status.error(null, null)
        }
    }

    override suspend fun getMedia(mediaId: Int): Status<MediaQuery.Media> {
        return try {
            val result = apiService.query(MediaQuery(mediaId)).await()
            Status.success(result.data?.media)
        } catch (e: ApolloException) {
            Status.error(null, null)
        }
    }
}