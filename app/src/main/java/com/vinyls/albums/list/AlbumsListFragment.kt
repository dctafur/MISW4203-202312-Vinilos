package com.vinyls.albums.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.vinyls.databinding.FragmentAlbumsListBinding

class AlbumsListFragment : Fragment() {

    private var _binding: FragmentAlbumsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val albumsViewModel = ViewModelProvider(this)[AlbumsViewModel::class.java]

        _binding = FragmentAlbumsListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val albumsList: RecyclerView = binding.albumsList
        albumsViewModel.albums.observe(viewLifecycleOwner) {
            albumsList.adapter = AlbumsListAdapter(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}