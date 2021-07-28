package com.example.spacexfanapp.UI

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
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
                Log.e("SRC_RocketDetailFrag","ROCKET SİNGLE : "+rocket.company);
                binding.txtName.text = rocket.name
                binding.imgSlider.load(rocket.flickr_images[0]){
                    crossfade(true)
                    crossfade(1000)
                }

                binding.txtHeight.text = "feet : "+rocket.height.feet+" / meters : "+rocket.height.meters
                binding.txtDiameter.text = "feet : "+rocket.diameter.feet+ " / meters : "+rocket.diameter.meters
                binding.txtMass.text = "Kg : "+rocket.mass.kg+" / lb : "+rocket.mass.lb
                binding.txtCompany.text = rocket.company
                binding.txtType.text = rocket.type
                binding.txtfirstFlight.text = rocket.first_flight
                binding.txtCountry.text = rocket.country
                var rocketState : String?

                if(rocket.active){
                    rocketState = "Active"
                    binding.txtActive.setTextColor(Color.GREEN)
                } else {
                    binding.txtActive.setTextColor(Color.RED)
                    rocketState = "Not Active"
                }
                binding.txtActive.text = rocketState

                binding.txtDesc.text = rocket.description
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