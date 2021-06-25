package com.example.spacexfanapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexfanapp.models.launchers.Launches
import com.example.spacexfanapp.models.rockets.Rocket
import com.example.spacexfanapp.repository.LaunchesRepository
import com.example.spacexfanapp.repository.RocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LaunchesViewModel

@Inject
constructor(val launchesRepository: LaunchesRepository) : ViewModel() {

    val _response = MutableLiveData<List<Launches>>()
    val _responseDetail = MutableLiveData<Launches>()

    val responseLaunchesShow: LiveData<List<Launches>>
        get() = _response

    val responseLaunchDetail: LiveData<Launches>
        get() = _responseDetail
    

    init {
        getAllLaunches()
        //getLaunchDetail("5eb87cd9ffd86e000604b32a")
    }

    private fun getAllLaunches() = viewModelScope.launch {

        launchesRepository.getLaunches().let { response ->
            if(response.isSuccessful){
                _response.postValue(response.body())
                //Log.e("SRC_LncViewModel","getLaunchesAll : ${response.body()}")
            }else{
                Log.e("SRC_LncViewModel","getLaunches Error: ${response.code()}")
            }
        }
    }//fun

    private fun getLaunchDetail(idm:String) = viewModelScope.launch {

        launchesRepository.getLaunch(idm).let { response ->
            if(response.isSuccessful){
                _responseDetail.postValue(response.body())
                Log.e("SRC_LncViewModel","getLaunch detail1 : ${response.body()}")
            }else{
                Log.e("SRC_LncViewModel","getLaunch Error: ${response.code()}")
            }
        }
    }//fun
}//cons


