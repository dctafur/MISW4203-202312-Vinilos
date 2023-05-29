package com.vinyls.collectors.details

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
import com.vinyls.databinding.FragmentCollectorsDetailsBinding

class CollectorsDetailsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCollectorsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val collectorsDetailsViewModel = ViewModelProvider(this)[CollectorsDetailsViewModel::class.java]
        arguments?.let { collectorsDetailsViewModel.getCollector(it.getInt("id")) }

        _binding = FragmentCollectorsDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val closeButton: ImageButton = binding.closeButton
        closeButton.setOnClickListener { dismiss() }

        val nameView: TextView = binding.collectorName
        val emailView: TextView = binding.collectorEmail
        val telephoneView: TextView = binding.collectorTelephone
        collectorsDetailsViewModel.collector.observe(viewLifecycleOwner) {
            nameView.text = it.name
            emailView.text = it.email
            telephoneView.text = it.telephone
        }

        val albumsList: RecyclerView = binding.collectorAlbumsList
        collectorsDetailsViewModel.albums.observe(viewLifecycleOwner) {
            albumsList.adapter = AlbumsListAdapter(it, object: AlbumsListAdapter.OnClickListener {
                override fun onClick(item: Album) {
                    Toast.makeText(context, "Clicked item", Toast.LENGTH_SHORT).show()
                }
            })
        }

        collectorsDetailsViewModel.status.observe(viewLifecycleOwner) { status ->
            when(status) {
                CollectorDetailsStatus.ERROR -> {
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