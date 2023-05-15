package com.vinyls.performers.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.vinyls.databinding.FragmentPerformerListBinding

class PerformerListFragment : Fragment() {

        private var _binding: FragmentPerformerListBinding? = null

        private val binding get() = _binding!!

        override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
        ): View {
                val performerViewModel = ViewModelProvider(this)[PerformerListViewModel::class.java]

                _binding = FragmentPerformerListBinding.inflate(inflater, container, false)
                val root: View = binding.root

                val performerList: RecyclerView = binding.performerList
                performerViewModel.performers.observe(viewLifecycleOwner) {
                        performerList.adapter = PerformerListAdapter(it)
                }

                return root
        }

        override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
        }
}