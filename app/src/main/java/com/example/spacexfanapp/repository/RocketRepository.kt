package com.example.spacexfanapp.repository

import androidx.lifecycle.LiveData
import com.example.spacexfanapp.api.RocketService
import javax.inject.Inject

class RocketRepository

@Inject
constructor(private val rocketService: RocketService) {
        suspend fun getRockets() = rocketService.getRockets()
        suspend fun getRocket(id:String) = rocketService.getRocket(id) //
    }


