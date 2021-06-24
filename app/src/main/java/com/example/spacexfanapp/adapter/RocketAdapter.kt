package com.example.spacexfanapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.spacexfanapp.databinding.RocketLayoutAdapterBinding
import com.example.spacexfanapp.models.rockets.Rocket

class RocketAdapter : RecyclerView.Adapter<RocketAdapter.RocketViewHolder>(){

    inner class RocketViewHolder(val binding:RocketLayoutAdapterBinding):
        RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<Rocket>(){

        override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {

            return newItem == oldItem

        }
    }


    private val differ = AsyncListDiffer(this,diffCallback)

    var rocketShows:List<Rocket>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        return RocketViewHolder(RocketLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val currentTvshow = rocketShows[position]

        holder.binding.apply {
            txtRocketName.text = currentTvshow.name

            imageView2.load(currentTvshow.flickr_images[0]){
                crossfade(true)
                crossfade(1000)
            }

            cardViewRocket.setOnClickListener(View.OnClickListener {
                Log.e("SERCAN","get Rocket Id : "+currentTvshow.id);

            });
        }


    }

    override fun getItemCount() = rocketShows.size

}