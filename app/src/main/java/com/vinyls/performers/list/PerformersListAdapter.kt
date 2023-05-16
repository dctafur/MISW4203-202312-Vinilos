package com.vinyls.performers.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.vinyls.R
import com.vinyls.performers.Performer

class PerformersListAdapter(
    private val dataset: List<Performer>
) : RecyclerView.Adapter<PerformersListAdapter.PerformerViewHolder>() {

    class PerformerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.performer_image)
        val nameView: TextView = view.findViewById(R.id.performer_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformerViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_performer_item, parent, false)
        return PerformerViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PerformerViewHolder, position: Int) {
        val item = dataset[position]

        holder.nameView.text = item.name
        item.image.let {
            val imgUri = item.image.toUri().buildUpon().scheme("https").build()
            holder.imageView.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image_black_24dp)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}