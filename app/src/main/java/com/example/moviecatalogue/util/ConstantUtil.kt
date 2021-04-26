package com.example.moviecatalogue.util

var BASE_ANILIST_URL = "https://graphql.anilist.co/"
const val BASE_TEST_PORT = 9090

enum class MessageType {
    LOAD, EXISTS, NOT_FOUND, ERROR
}