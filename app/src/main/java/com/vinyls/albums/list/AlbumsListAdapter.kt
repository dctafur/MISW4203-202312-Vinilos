package com.vinyls.albums.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.vinyls.R
import com.vinyls.albums.Album

class AlbumsListAdapter(
    private val dataset: List<Album>,
    private val onClick: OnClickListener,
    ) : RecyclerView.Adapter<AlbumsListAdapter.AlbumViewHolder>() {

    interface OnClickListener {
        fun onClick(item: Album)
    }

    class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coverView: ImageView = view.findViewById(R.id.album_cover)
        val nameView: TextView = view.findViewById(R.id.album_name)
        val releaseYearView: TextView = view.findViewById(R.id.album_release_year)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_album_item, parent, false)
        return AlbumViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = dataset[position]

        holder.nameView.text = item.name
        holder.releaseYearView.text = item.releaseDate.split("-")[0]
        holder.itemView.setOnClickListener { onClick.onClick(item) }

        item.cover.let {
            val imgUri = item.cover.toUri().buildUpon().scheme("https").build()
            holder.coverView.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image_black_24dp)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}