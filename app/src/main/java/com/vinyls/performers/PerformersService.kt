package com.vinyls.performers

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://misw-4203-vynils.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PerformersService {

    @GET("bands")
    suspend fun getPerformers(): List<Performer>

    @GET("bands/{id}")
    suspend fun getPerformer(@Path("id") id: Int): Performer
}

object PerformersApi {
    val retrofitService: PerformersService by lazy { retrofit.create(PerformersService::class.java) }
}