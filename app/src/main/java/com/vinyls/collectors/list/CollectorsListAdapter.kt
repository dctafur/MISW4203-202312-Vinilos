package com.vinyls.collectors.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.vinyls.R
import com.vinyls.collectors.Collector

class CollectorsListAdapter(
    private val dataset: List<Collector>,
    private val onClick: OnClickListener,
    private val onAggregate: OnClickListener
) : RecyclerView.Adapter<CollectorsListAdapter.CollectorViewHolder>() {

    interface OnClickListener {
        fun onClick(item: Collector)
    }

    class CollectorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.collector_name)
        val emailView: TextView = view.findViewById(R.id.collector_email)
        val optionsButton: ImageButton = view.findViewById(R.id.options_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_collector_item, parent, false)
        return CollectorViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        val item = dataset[position]
        holder.nameView.text = item.name
        holder.emailView.text = item.email
        holder.itemView.setOnClickListener { onClick.onClick(item) }

        val button = holder.optionsButton
        val popupMenu = PopupMenu(holder.itemView.context, button)
        button.setOnClickListener { popupMenu.show() }

        popupMenu.menuInflater.inflate(R.menu.collector_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.aggregate_album -> {
                    onAggregate.onClick(item)
                    true
                }
                else -> false
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}