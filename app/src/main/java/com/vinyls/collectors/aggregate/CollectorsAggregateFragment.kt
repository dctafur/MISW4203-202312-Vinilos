package com.vinyls.collectors.aggregate

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vinyls.albums.Album
import com.vinyls.albums.list.AlbumsListAdapter

import com.vinyls.databinding.FragmentCollectorsAggregateBinding

class CollectorsAggregateFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCollectorsAggregateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val collectorsAggregateViewModel = ViewModelProvider(this)[CollectorsAggregateViewModel::class.java]

        _binding = FragmentCollectorsAggregateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val closeButton: ImageButton = binding.closeButton
        closeButton.setOnClickListener { dismiss() }

        val albumsList: RecyclerView = binding.albumsList
        collectorsAggregateViewModel.albums.observe(viewLifecycleOwner) { data ->
            albumsList.adapter = AlbumsListAdapter(data, object: AlbumsListAdapter.OnClickListener {
                override fun onClick(item: Album) {
                    arguments?.let {
                        collectorsAggregateViewModel.aggregateAlbum(it.getInt("id"), item)
                    }
                }
            })
        }

        collectorsAggregateViewModel.status.observe(viewLifecycleOwner) { status ->
            when(status) {
                CollectorsAggregateStatus.DONE -> {
                    Toast.makeText(context, "¡Se ha asociado el album exitosamente!", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
                CollectorsAggregateStatus.ERROR -> {
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