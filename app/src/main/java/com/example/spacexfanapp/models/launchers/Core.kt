package com.example.spacexfanapp.models.launchers

data class Core(
    val core: String,
    val flight: Int,
    val gridfins: Boolean,
    val landing_attempt: Boolean,
    val landing_success: Any,
    val landing_type: Any,
    val landpad: Any,
    val legs: Boolean,
    val reused: Boolean
)