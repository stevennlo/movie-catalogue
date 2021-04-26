package com.example.moviecatalogue.service

import com.apollographql.apollo.ApolloClient
import com.example.moviecatalogue.util.BASE_ANILIST_URL

object AniListApiService {
    private var mApolloClient: ApolloClient? = null

    fun getInstance(): ApolloClient {
        if (mApolloClient == null) {
            synchronized(this) {
                mApolloClient = ApolloClient.builder()
                    .serverUrl(BASE_ANILIST_URL)
                    .okHttpClient(OkHttpService.getClient())
                    .build()
            }
        }
        return mApolloClient as ApolloClient
    }
}

class Status<T>(val status: StatusType, val data: T?, val message: String?) {
    enum class StatusType {
        SUCCESS, FAILED
    }

    companion object {
        fun <T> success(data: T?): Status<T> {
            return Status(StatusType.SUCCESS, data, null)
        }

        fun <T> error(message: String?, data: T?): Status<T> {
            return Status(StatusType.FAILED, data, message)
        }
    }
}