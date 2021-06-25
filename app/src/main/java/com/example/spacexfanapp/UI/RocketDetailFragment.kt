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
import com.example.spacexfanapp.databinding.FragmentLaunchDetailBinding
import com.example.spacexfanapp.viewmodel.RocketViewModel


class RocketDetailFragment : Fragment() {

    private lateinit var viewModel: RocketViewModel

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    var rocketId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(RocketViewModel::class.java)

         arguments?.getString("rocketId","")?.let {
             rocketId = it
         }

        if(!rocketId.isEmpty()){

            viewModel.getRocket(rocketId)
            viewModel.responseRocketDetail.observe(requireActivity(), {rocket ->
                Log.e("SRC_RocketDetailFrag","ROCKET SİNGLE : "+rocket.toString());
                binding.txtName.text = rocket.name
            })

        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            RocketDetailFragment().apply {

            }
    }
}