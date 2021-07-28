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
import com.example.spacexfanapp.databinding.FavoriteLayoutAdapterBinding
import com.example.spacexfanapp.databinding.RocketLayoutAdapterBinding
import com.example.spacexfanapp.entity.FavoriteEntity
import com.example.spacexfanapp.models.rockets.Rocket

class FavoriteAdapter(private val listener: FavoriteAdapter.IFavoriteItemListener) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){

    interface IFavoriteItemListener {
        fun onClickedFavorite(Id: String)
    }

    inner class FavoriteViewHolder(val binding: FavoriteLayoutAdapterBinding, private val listener: FavoriteAdapter.IFavoriteItemListener)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private lateinit var rocket: Rocket
        init {
            binding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: Rocket) {
            this.rocket = item
        }
        override fun onClick(v: View?) {
            listener.onClickedFavorite(rocket.id)
        }

    }

    private val diffCallback = object : DiffUtil.ItemCallback<FavoriteEntity>() {

        override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {

            return oldItem.Id == newItem.Id

        }

        override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {

            return newItem == oldItem

        }
    }


    private val differ = AsyncListDiffer(this,diffCallback)

    var favoriteShows:List<FavoriteEntity>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.FavoriteViewHolder {
        return FavoriteViewHolder(FavoriteLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false),listener)
    }

    override fun getItemCount(): Int {
        return favoriteShows.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

        val currentTvshow = favoriteShows[position]

        holder.binding.apply {
            txtFavoriteName.text = currentTvshow.name


            imageView2.load(currentTvshow.Image){
                crossfade(true)
                crossfade(1000)
            }

            cardViewFavorite.setOnClickListener(View.OnClickListener {
                Log.e("SRC_FavoriteAdapter","get Favorite Id : "+currentTvshow.uid);

                listener.onClickedFavorite(currentTvshow.uid)
            });
        }
    }


}