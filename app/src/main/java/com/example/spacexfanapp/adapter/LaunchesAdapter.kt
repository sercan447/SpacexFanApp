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
import com.example.spacexfanapp.database.AppDatabase
import com.example.spacexfanapp.databinding.LaunchesLayoutAdapterBinding
import com.example.spacexfanapp.entity.FavoriteEntity
import com.example.spacexfanapp.models.launchers.Launches

class LaunchesAdapter(private val listener: LaunchItemListener)  : RecyclerView.Adapter<LaunchesAdapter.LaunchesViewHolder>(){

    var appDatabase: AppDatabase? = null;

    interface LaunchItemListener {
        fun onClickedLaunch(Id: String)
    }

    inner class LaunchesViewHolder(val binding:LaunchesLayoutAdapterBinding,private val listener: LaunchesAdapter.LaunchItemListener):
        RecyclerView.ViewHolder(binding.root),View.OnClickListener{

        private lateinit var launches: Launches
        init {
            binding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: Launches) {
            this.launches = item
        }
        override fun onClick(v: View?) {
            Log.e("SERCAN","viewholder")
            listener.onClickedLaunch(launches.id)
        }

    }


    private val diffCallback = object : DiffUtil.ItemCallback<Launches>(){

        override fun areItemsTheSame(oldItem: Launches, newItem: Launches): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Launches, newItem: Launches): Boolean {
            return newItem == oldItem
        }
    }
    private val differ = AsyncListDiffer(this,diffCallback)

    var launchesShows:List<Launches>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesViewHolder {

        appDatabase = AppDatabase.getDatabase(parent.context)

        return LaunchesViewHolder(LaunchesLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false),listener)

    }

    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) {
        val currentLaunchesShow = launchesShows[position]

        holder.binding.apply {
            txtLaunchesName.text = currentLaunchesShow.name

            imgSmall.load(currentLaunchesShow.links.patch.small){
                crossfade(true)
                crossfade(1000)
            }

            cardViewLaunch.setOnClickListener(View.OnClickListener {
                Log.e("SERCAN","getLaunch Id : "+currentLaunchesShow.id);

               // val bundle = Bundle()
                //    bundle.putString("launchId",currentLaunchesShow.id)
                listener.onClickedLaunch(currentLaunchesShow.id)

            });

            val isChk = isFavoriCheckAndInsert(currentLaunchesShow.id)
            if(isChk){
                btnFavoriAdd.setText("Added")
            }else{
                btnFavoriAdd.setText("Favorite Add")
            }

            btnFavoriAdd.setOnClickListener(View.OnClickListener {
                Log.e("SRC","tıklanan: "+currentLaunchesShow.id);

                val isChk = isFavoriCheckAndInsert(currentLaunchesShow.id)
                if(!isChk){

                    val favoriteInsert: FavoriteEntity = FavoriteEntity()
                    favoriteInsert.uid = currentLaunchesShow.id
                    favoriteInsert.name = currentLaunchesShow.name
                    favoriteInsert.Image = currentLaunchesShow.links.patch.small

                    appDatabase?.favoriteDao()?.insert(favoriteInsert)

                    btnFavoriAdd.setText("Added")
                }else{
                    appDatabase?.favoriteDao()?.deleteId(currentLaunchesShow.id!!)

                    btnFavoriAdd.setText("Favorite Add")
                }
            })
        }
    }
    fun isFavoriCheckAndInsert(uid:String):Boolean{

        var a = appDatabase?.favoriteDao()?.findById(uid)

        return a != null
    }

    override fun getItemCount() = launchesShows.size

}