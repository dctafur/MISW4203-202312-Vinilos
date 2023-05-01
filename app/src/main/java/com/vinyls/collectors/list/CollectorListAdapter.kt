package com.vinyls.collectors.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.vinyls.R
import com.vinyls.collectors.Collector

class CollectorListAdapter(
    private val dataset: List<Collector>
) : RecyclerView.Adapter<CollectorListAdapter.CollectorViewHolder>() {

    class CollectorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val email: TextView = view.findViewById(R.id.collector_email)
        val nameView: TextView = view.findViewById(R.id.collector_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_collector_item, parent, false)
        return CollectorViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        val item = dataset[position]

        holder.nameView.text = item.name
        holder.email.text = item.email
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}