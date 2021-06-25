package com.example.spacexfanapp.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.spacexfanapp.R
import com.example.spacexfanapp.databinding.FragmentDetailBinding
import com.example.spacexfanapp.databinding.FragmentFavoritesBinding
import com.example.spacexfanapp.databinding.FragmentLaunchDetailBinding
import com.example.spacexfanapp.databinding.FragmentLauncherBinding
import com.example.spacexfanapp.viewmodel.LaunchesViewModel


class LaunchDetailFragment : Fragment() {

    private var _binding : FragmentLaunchDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LaunchesViewModel

    var launchId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLaunchDetailBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(LaunchesViewModel::class.java)

        arguments?.getString("launchId","")?.let {
            launchId = it
        }

        if(!launchId.isEmpty()){

            viewModel.getLaunchDetail(launchId)

            viewModel.responseLaunchDetail.observe(requireActivity(), { launches ->
                Log.e("SRC_Launchdetail","Launch : "+launches)
                binding.txtName.text = launches.name
            })
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LaunchDetailFragment().apply {

            }
    }
}