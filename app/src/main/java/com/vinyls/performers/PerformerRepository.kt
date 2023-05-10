package com.vinyls.performers

class PerformerRepository {

    suspend fun getPerformers(): List<Performer> {
        val performers = PerformersCacheManager.getInstance().getPerformers()
        if (performers.isNullOrEmpty()) {
            val items = PerformerApi.retrofitService.getPerformers()
            PerformersCacheManager.getInstance().addPerformers(items)
            return items
        }
        return performers
    }
}