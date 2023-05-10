package com.vinyls.collectors

class CollectorsCacheManager {

    companion object {

        @Volatile
        private var instance: CollectorsCacheManager? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CollectorsCacheManager().also { instance = it }
            }
    }

    private var collectors: ArrayList<Collector>? = null

    fun addCollector(item: Collector) {
        if (collectors == null) collectors = ArrayList()
        collectors!!.add(item)
    }

    fun addCollectors(items: List<Collector>) {
        if (collectors == null) collectors = ArrayList()
        collectors!!.addAll(items)
    }

    fun getCollectors(): List<Collector>? {
        return collectors
    }
}