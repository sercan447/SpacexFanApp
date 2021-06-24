package com.example.spacexfanapp.repository

import com.example.spacexfanapp.api.LaunchService
import javax.inject.Inject

class LaunchesRepository

    @Inject
    constructor(private val lanchesService: LaunchService) {
        suspend fun getLaunches() = lanchesService.getLaunches()
        suspend fun getLaunch(id:String) = lanchesService.getLaunch(id)
    }




