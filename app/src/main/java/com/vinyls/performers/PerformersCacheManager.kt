package com.vinyls.performers

class PerformersCacheManager {

    companion object {

        @Volatile
        private var instance: PerformersCacheManager? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: PerformersCacheManager().also { instance = it }
            }
    }

    private var performers: ArrayList<Performer>? = null

    fun addPerformer(item: Performer) {
        if (performers == null) performers = ArrayList()
        performers!!.add(item)
    }

    fun addPerformers(items: List<Performer>) {
        if (performers == null) performers = ArrayList()
        performers!!.addAll(items)
    }

    fun getPerformers(): List<Performer>? {
        return performers
    }
}