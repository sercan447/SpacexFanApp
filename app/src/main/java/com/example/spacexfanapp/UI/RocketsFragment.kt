package com.example.spacexfanapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexfanapp.R
import com.example.spacexfanapp.adapter.RocketAdapter
import com.example.spacexfanapp.databinding.ActivityMainBinding
import com.example.spacexfanapp.databinding.FragmentRocketsBinding
import com.example.spacexfanapp.viewmodel.RocketViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RocketsFragment : Fragment() {

    private lateinit var rocketadapter:RocketAdapter

    private var _binding : FragmentRocketsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RocketViewModel
   // private val viewModel: RocketViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rocketadapter = RocketAdapter()
        viewModel = ViewModelProvider(requireActivity()).get(RocketViewModel::class.java)
        _binding = FragmentRocketsBinding.inflate(inflater,container,false)

        binding.recyclerviewRockets.apply {
            adapter = rocketadapter
            //layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            layoutManager =  GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);

            //setHasFixedSize(true)
        }

        viewModel.rocketsList.observe(requireActivity(), {listRocket ->
            rocketadapter.rocketShows = listRocket

        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RocketsFragment().apply {
            }
    }
}


