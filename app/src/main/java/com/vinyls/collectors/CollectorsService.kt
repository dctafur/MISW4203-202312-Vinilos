package com.vinyls.collectors

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "https://misw-4203-vynils.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CollectorsService {

    @GET("collectors")
    suspend fun getCollectors(): List<Collector>

    @GET("collectors/{id}")
    suspend fun getCollector(@Path("id") id: Int): Collector

    @GET("collectors/{id}/albums")
    suspend fun getCollectorAlbums(@Path("id") id: Int): List<CollectorAlbum>

    @POST("collectors/{collectorId}/albums/{albumId}")
    suspend fun aggregateAlbum(
        @Path("collectorId") collectorId: Int,
        @Path("albumId") albumId: Int,
        @Body body: AggregateAlbum
    ): CollectorAlbum
}

object CollectorsApi {
    val retrofitService: CollectorsService by lazy { retrofit.create(CollectorsService::class.java) }
}