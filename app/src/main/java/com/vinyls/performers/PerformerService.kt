package com.vinyls.performers

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vinyls.performers.Performer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://misw-4203-vynils.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PerformerService {

    @GET("bands")
    suspend fun getPerformers(): List<Performer>
}

object PerformerApi {
    val retrofitService: PerformerService by lazy { retrofit.create(PerformerService::class.java) }
}