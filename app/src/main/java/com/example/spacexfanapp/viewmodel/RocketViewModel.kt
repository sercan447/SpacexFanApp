package com.example.spacexfanapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexfanapp.models.rockets.Rocket
import com.example.spacexfanapp.repository.RocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RocketViewModel

@Inject
constructor(val rocketRepository: RocketRepository) : ViewModel() {

    val _rocketAll =  MutableLiveData<List<Rocket>>()
    val _responseDetail =  MutableLiveData<Rocket>()

    val rocketsList: LiveData<List<Rocket>>
        get() = _rocketAll
    val responseRocketDetail: LiveData<Rocket>
        get() = _responseDetail

    init {
        getAllRockets()
        //getRocket("5e9d0d95eda69955f709d1eb")
    }

    private fun getAllRockets() = viewModelScope.launch {

        rocketRepository.getRockets().let { response ->
            if(response.isSuccessful){
                _rocketAll.postValue(response.body())
            }else{
                Log.e("SERCAN","getRockets Error: ${response.code()}")
            }
        }
    }//fun

    private fun getRocket(id:String) = viewModelScope.launch {

        rocketRepository.getRocket(id).let { response ->
            if(response.isSuccessful){
                _responseDetail.postValue(response.body())
                 Log.e("SERCAN","getRocket detail1: ${response.body()}")
            }else{
                Log.e("SERCAN","getRocket Error: ${response.code()}")
            }
        }
    }//fun

}//cons

