package com.tube.ktorclientsite.data.remote

import com.tube.ktorclientsite.data.remote.dto.PostRequest
import com.tube.ktorclientsite.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface PostsAPIService {
    suspend fun getPosts(): List<PostResponse>
    suspend fun createPosts(postRequest: PostRequest): PostResponse?


    companion object {
        fun create(): PostsAPIService {
            return PostsServiceImpl(
                client = HttpClient(Android) {
                    install(Logging){
                        level = LogLevel.ALL
                    }

                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}