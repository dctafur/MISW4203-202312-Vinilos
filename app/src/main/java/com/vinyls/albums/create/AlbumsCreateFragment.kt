package com.vinyls.albums.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

import com.vinyls.albums.Album
import com.vinyls.databinding.FragmentAlbumsCreateBinding

class AlbumsCreateFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAlbumsCreateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val albumsCreateViewModel = ViewModelProvider(this)[AlbumsCreateViewModel::class.java]

        _binding = FragmentAlbumsCreateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val closeButton: ImageButton = binding.closeButton
        closeButton.setOnClickListener { dismiss() }

        val cancelButton: Button = binding.cancelButton
        cancelButton.setOnClickListener { dismiss() }

        val name: TextInputLayout = binding.nameField

        val addButton: Button = binding.addButton
        addButton.setOnClickListener {
            val album = Album(
                id = 0,
                name = name.editText?.text.toString(),
                cover = "",
                description = "",
                releaseDate = "",
                genre = "",
            )
            albumsCreateViewModel.createAlbum(album)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}