package com.example.spacexfanapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spacexfanapp.R
import com.example.spacexfanapp.viewmodel.RocketViewModel


class RocketDetailFragment : Fragment() {

    private lateinit var viewModelRocket: RocketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelRocket.responseRocketDetail.observe(requireActivity(), {rocket ->

        })


        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RocketDetailFragment().apply {
            return RocketDetailFragment()
            }
    }
}