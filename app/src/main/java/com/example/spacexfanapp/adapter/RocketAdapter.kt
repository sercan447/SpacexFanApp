package com.example.spacexfanapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.spacexfanapp.databinding.RocketLayoutAdapterBinding
import com.example.spacexfanapp.models.launchers.Launches
import com.example.spacexfanapp.models.rockets.Rocket

class RocketAdapter(private val listener: RocketAdapter.IRocketItemListener) : RecyclerView.Adapter<RocketAdapter.RocketViewHolder>(){

    interface IRocketItemListener {
        fun onClickedRocket(Id: String)
    }

    inner class RocketViewHolder(val binding:RocketLayoutAdapterBinding,private val listener: RocketAdapter.IRocketItemListener)
        : RecyclerView.ViewHolder(binding.root),View.OnClickListener{

        private lateinit var rocket: Rocket
        init {
            binding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: Rocket) {
            this.rocket = item
        }
        override fun onClick(v: View?) {
            listener.onClickedRocket(rocket.id)
        }

        }


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
        return RocketViewHolder(RocketLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false),listener)
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
                Log.e("SRC_RocketAdapter","get Rocket Id : "+currentTvshow.id);

                listener.onClickedRocket(currentTvshow.id)
            });
        }


    }

    override fun getItemCount() = rocketShows.size

}