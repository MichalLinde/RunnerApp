package com.mlinde.runner

data class Trail(
    val id: Int,
    val name: String,
    val length: Int,
    val lat_start: Float,
    val lng_start: Float,
    val lat_end: Float,
    val lng_end: Float,
    val description: String
)
