package com.vinyls.albums.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vinyls.albums.Album

import com.vinyls.albums.create.AlbumsCreateFragment
import com.vinyls.albums.details.AlbumsDetailsFragment
import com.vinyls.databinding.FragmentAlbumsListBinding

class AlbumsListFragment : Fragment() {

    private var _binding: FragmentAlbumsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val albumsListViewModel = ViewModelProvider(this)[AlbumsListViewModel::class.java]

        _binding = FragmentAlbumsListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val albumsList: RecyclerView = binding.albumsList
        albumsListViewModel.albums.observe(viewLifecycleOwner) {
            albumsList.adapter = AlbumsListAdapter(it, object: AlbumsListAdapter.OnClickListener {
                override fun onClick(item: Album) {
                    val bundle = Bundle()
                    val dialog = AlbumsDetailsFragment()
                    bundle.putInt("id", item.id!!)
                    dialog.arguments = bundle
                    dialog.show(childFragmentManager, "albums-details")
                }
            })
        }

        val addAlbum: FloatingActionButton = binding.addAlbum
        addAlbum.setOnClickListener {
            val dialog = AlbumsCreateFragment()
            dialog.show(childFragmentManager, "albums-create")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}