package com.vinyls.performers

class PerformerRepository {

    suspend fun getPerformers(): List<Performer> {
        return PerformerApi.retrofitService.getPerformers()
    }
}