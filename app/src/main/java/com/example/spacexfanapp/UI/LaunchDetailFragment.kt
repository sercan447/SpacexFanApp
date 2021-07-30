package com.example.spacexfanapp.UI

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.lifecycle.ViewModelProvider
import com.example.spacexfanapp.R
import com.example.spacexfanapp.databinding.FragmentDetailBinding
import com.example.spacexfanapp.databinding.FragmentFavoritesBinding
import com.example.spacexfanapp.databinding.FragmentLaunchDetailBinding
import com.example.spacexfanapp.databinding.FragmentLauncherBinding
import com.example.spacexfanapp.viewmodel.LaunchesViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener


class LaunchDetailFragment : Fragment() ,YouTubePlayer{

    private var _binding : FragmentLaunchDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LaunchesViewModel

    var launchId = ""
    var video_Id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    var mediaController : MediaController? = null
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


                if(mediaController == null)
                    mediaController = MediaController(context)

                    mediaController!!.setAnchorView(binding.vdioYoutube)

                lifecycle.addObserver(binding.vdioYoutube)

                video_Id = launches.links.youtube_id

                //binding.vdioYoutube.setVideoPath(launches.links.webcast)
                    //binding.vdioYoutube.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=UQ4Yg_HUBFY"))
                    //binding.vdioYoutube.setMediaController(mediaController)
                    //binding.vdioYoutube.requestFocus();
                   // binding.vdioYoutube.start()

                binding.txtDetails.text = launches.details

                if(launches.upcoming) {
                    binding.txtUpcoming.setTextColor(Color.RED)
                    binding.txtUpcoming.text = "No"

                }else {
                    binding.txtUpcoming.setTextColor(Color.GREEN)
                    binding.txtUpcoming.text = "Yes"
                }

                binding.txtwiki.setOnClickListener {
                    var browserIntent:Intent = Intent(Intent.ACTION_VIEW, Uri.parse(launches.links.wikipedia));
                    startActivity(browserIntent);
                }
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

    override fun addListener(listener: YouTubePlayerListener): Boolean {
        listener.onVideoId(this,video_Id)

        return true
    }

    override fun cueVideo(videoId: String, startSeconds: Float) {

    }

    override fun loadVideo(videoId: String, startSeconds: Float) {

    }

    override fun mute() {

    }

    override fun pause() {

    }

    override fun play() {

    }

    override fun removeListener(listener: YouTubePlayerListener): Boolean {

    }

    override fun seekTo(time: Float) {

    }

    override fun setVolume(volumePercent: Int) {

    }

    override fun unMute() {

    }
}