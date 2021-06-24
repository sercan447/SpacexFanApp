package com.example.spacexfanapp.models.rockets

data class FirstStage(
    val burn_time_sec: Int,
    val engines: Double, // old Int
    val fuel_amount_tons: Double,
    val reusable: Boolean,
    val thrust_sea_level: ThrustSeaLevelX,
    val thrust_vacuum: ThrustVacuumX
)