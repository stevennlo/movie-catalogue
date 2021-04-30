package com.example.moviecatalogue.util

const val BASE_ANILIST_URL = "https://graphql.anilist.co/"

enum class MessageType {
    LOAD, EXISTS, NOT_FOUND, ERROR
}