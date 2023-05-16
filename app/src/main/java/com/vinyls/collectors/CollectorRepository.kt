package com.vinyls.collectors

class CollectorRepository {

    suspend fun getCollectors(): List<Collector> {
        return CollectorApi.retrofitService.getCollectors()
    }
}