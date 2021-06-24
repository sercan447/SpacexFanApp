package com.example.spacexfanapp.models.rockets

data class Engines(
    val engine_loss_max: Int,
    val isp: İsp,
    val layout: String,
    val number: Int,
    val propellant_1: String,
    val propellant_2: String,
    val thrust_sea_level: ThrustSeaLevel,
    val thrust_to_weight: Double, //old Int
    val thrust_vacuum: ThrustVacuum,
    val type: String,
    val version: String
)