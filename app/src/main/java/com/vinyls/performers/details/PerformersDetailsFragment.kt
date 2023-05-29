package com.vinyls.performers.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vinyls.albums.Album

import com.vinyls.albums.list.AlbumsListAdapter
import com.vinyls.databinding.FragmentPerformersDetailsBinding

class PerformersDetailsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPerformersDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val performersDetailsViewModel = ViewModelProvider(this)[PerformersDetailsViewModel::class.java]
        arguments?.let { performersDetailsViewModel.getPerformer(it.getInt("id")) }

        _binding = FragmentPerformersDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val closeButton: ImageButton = binding.closeButton
        closeButton.setOnClickListener { dismiss() }

        val nameView: TextView = binding.performerName
        val descriptionView: TextView = binding.performerDescription
        performersDetailsViewModel.performer.observe(viewLifecycleOwner) {
            nameView.text = it.name
            descriptionView.text = it.description
        }

        val albumsList: RecyclerView = binding.performerAlbumsList
        performersDetailsViewModel.albums.observe(viewLifecycleOwner) {
            albumsList.adapter = AlbumsListAdapter(it, object: AlbumsListAdapter.OnClickListener {
                override fun onClick(item: Album) {
                    Toast.makeText(context, "Clicked item", Toast.LENGTH_SHORT).show()
                }
            })
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}