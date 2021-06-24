package com.example.spacexfanapp.api

import com.example.spacexfanapp.helpers.Constants
import com.example.spacexfanapp.models.launchers.Launches
import com.example.spacexfanapp.models.rockets.Rocket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LaunchService {

    @GET(Constants.END_POINT_LAUNCHES)
    suspend fun getLaunches(): Response<List<Launches>>

    @GET(Constants.END_POINT_LAUNCH)
    suspend fun getLaunch(@Path(value="id") id:String): Response<Launches>

}