package com.keepcoding.androidavanzado.ui.herolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.androidavanzado.R
import com.keepcoding.androidavanzado.databinding.FragmentHeroListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroListFragment : Fragment() {

    private var _binding: FragmentHeroListBinding? = null
    private val binding get() = _binding!!

    private val adapter = HeroAdapter()

    private val viewModel: HeroListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHeroListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            heroList
            myButton

            myButton.setOnClickListener {
                //viewModel.getHeroList()
                findNavController().navigate(R.id.action_heroListFragment_to_mapFragment)
            }

            heroList.adapter = adapter
            heroList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter.notifyDataSetChanged()
        }

        viewModel.heros.observe(viewLifecycleOwner) { heros ->
            adapter.addHeros(heros)
        }



    }

    companion object {
        @JvmStatic fun newInstance() =
            HeroListFragment().apply {
                arguments = Bundle()
            }
    }
}
