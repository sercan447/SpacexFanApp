package com.example.spacexfanapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexfanapp.R
import com.example.spacexfanapp.adapter.LaunchesAdapter
import com.example.spacexfanapp.databinding.FragmentLauncherBinding
import com.example.spacexfanapp.viewmodel.LaunchesViewModel


class LauncherFragment : Fragment() ,LaunchesAdapter.LaunchItemListener  {

    private lateinit var launchesAdapter: LaunchesAdapter

    private var _binding : FragmentLauncherBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LaunchesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        launchesAdapter = LaunchesAdapter(this)
        viewModel = ViewModelProvider(requireActivity()).get(LaunchesViewModel::class.java)
        _binding = FragmentLauncherBinding.inflate(inflater,container,false)


        binding.recyclerviewLaunchers.apply {

            adapter = launchesAdapter
           // layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            layoutManager =  GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

            //setHasFixedSize(true)
        }

        viewModel.responseLaunchesShow.observe(requireActivity(), { listLaunches ->
            launchesAdapter.launchesShows = listLaunches

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
            LauncherFragment().apply {

            }
    }

    override fun onClickedLaunch(Id: String) {
        val bundle = Bundle()
        bundle.putString("launchId",Id)

        findNavController().navigate(R.id.action_launcherDetailsFragment,bundle)

    }

}