package com.vinyls.collectors.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.vinyls.collectors.Collector
import com.vinyls.collectors.details.CollectorsDetailsFragment
import com.vinyls.databinding.FragmentCollectorsListBinding

class CollectorsListFragment : Fragment() {

    private var _binding: FragmentCollectorsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val collectorsViewModel = ViewModelProvider(this)[CollectorsListViewModel::class.java]

        _binding = FragmentCollectorsListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val collectorList: RecyclerView = binding.collectorsList
        collectorsViewModel.collectors.observe(viewLifecycleOwner) {
            collectorList.adapter = CollectorsListAdapter(it, object : CollectorsListAdapter.OnClickListener {
                override fun onClick(item: Collector) {
                    val bundle = Bundle()
                    val dialog = CollectorsDetailsFragment()
                    bundle.putInt("id", item.id)
                    dialog.arguments = bundle
                    dialog.show(childFragmentManager, "collectors-details")
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