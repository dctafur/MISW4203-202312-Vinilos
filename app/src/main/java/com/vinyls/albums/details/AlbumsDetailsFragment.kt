package com.vinyls.albums.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vinyls.databinding.FragmentAlbumsDetailsBinding

class AlbumsDetailsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAlbumsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val albumsDetailsViewModel = ViewModelProvider(this)[AlbumsDetailsViewModel::class.java]
        arguments?.let { albumsDetailsViewModel.getAlbum(it.getInt("id")) }

        _binding = FragmentAlbumsDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val closeButton: ImageButton = binding.closeButton
        closeButton.setOnClickListener { dismiss() }

        val nameView: TextView = binding.albumName
        val descriptionView: TextView = binding.albumDescription
        val genreView: TextView = binding.albumGenre
        val recordLabelView: TextView = binding.albumRecordLabel
        val releaseDateView: TextView = binding.albumReleaseDate
        albumsDetailsViewModel.album.observe(viewLifecycleOwner) {
            nameView.text = it.name
            descriptionView.text = it.description
            genreView.text = it.genre
            recordLabelView.text = it.recordLabel
            releaseDateView.text = it.releaseDate.subSequence(0, 10)
        }

        albumsDetailsViewModel.status.observe(viewLifecycleOwner) { status ->
            when(status) {
                AlbumDetailsStatus.ERROR -> {
                    Toast.makeText(context, "¡Ha ocurrido un error inesperado!", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
                else -> {}
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}