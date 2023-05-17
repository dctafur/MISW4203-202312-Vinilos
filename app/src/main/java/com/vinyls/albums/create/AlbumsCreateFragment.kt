package com.vinyls.albums.create

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
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
        val cover: TextInputLayout = binding.coverField
        val description: TextInputLayout = binding.descriptionField
        val releaseDate: TextInputLayout = binding.releaseDateField
        val genre: TextInputLayout = binding.genreField

        val addButton: Button = binding.addButton
        addButton.setOnClickListener {
            val album = Album(
                id = 0,
                name = name.editText?.text.toString(),
                cover = cover.editText?.text.toString(),
                description = description.editText?.text.toString(),
                releaseDate = releaseDate.editText?.text.toString(),
                genre = genre.editText?.text.toString(),
            )
            albumsCreateViewModel.createAlbum(album)
        }

        albumsCreateViewModel.status.observe(viewLifecycleOwner) { status ->
            when(status) {
                AlbumsCreateStatus.DONE -> {
                    Toast.makeText(context, "¡Se ha asociado el album exitosamente!", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
                AlbumsCreateStatus.ERROR -> {
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