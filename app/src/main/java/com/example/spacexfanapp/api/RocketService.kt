package com.example.spacexfanapp.api

import androidx.lifecycle.LiveData
import com.example.spacexfanapp.helpers.Constants
import com.example.spacexfanapp.models.rockets.Rocket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketService {

    @GET(Constants.END_POINT_ROCKETS)
    suspend fun getRockets(): Response<List<Rocket>>

    @GET(Constants.END_POINT_ROCKET)
    suspend fun getRocket(@Path(value="id") id:String): Response<Rocket>

}