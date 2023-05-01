package com.vinyls.collectors.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.vinyls.databinding.FragmentCollectorListBinding

class CollectorListFragment : Fragment() {

        private var _binding: FragmentCollectorListBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
        ): View {
                val collectorViewModel = ViewModelProvider(this)[CollectorListViewModel::class.java]

                _binding = FragmentCollectorListBinding.inflate(inflater, container, false)
                val root: View = binding.root

                val collectorList: RecyclerView = binding.collectorList
                collectorViewModel.collectors.observe(viewLifecycleOwner) {
                        collectorList.adapter = CollectorListAdapter(it)
                }

                return root
        }

        override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
        }
}