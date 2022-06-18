package com.mlinde.runner

import java.io.Serializable

data class Trail(
    val city: String? = null,
    val description: String? = null,
    val difficulty: String? = null,
    val distance: Int? = null,
    val id: Int? = null,
    val lat_end: Double? = null,
    val lat_start: Double? = null,
    val lng_end: Double? = null,
    val lng_start: Double? = null,
    val name: String? = null,
    val photo: String? = null
) : Serializable

//val trails = listOf(
//    Trail(1, "Jezioro Maltańskie", 2900, 52.4048152, 16.9550043, 52.4003092, 16.9875468, "Peaceful trail along the lake. Even pavement and pretty surrounding guarantee amazing run.", "Medium", "Poznań" ),
//    Trail(2, "Park Wilsona", 600, 52.3987426, 16.9034073, 52.4013642, 16.8999476, "Quick trail if you have little time, but still want to run. Running next to the Wilson Park guarantees beautiful view of nature.", "Easy", "Poznań"),
//    Trail(3, "Park Cytadela",1000, 52.4180385, 16.9405052, 52.4248945, 16.9345521, "Run across the Cytadela Park and enjoy the view. You can meet many other runners or skaters. Have a nice run!", "Easy", "Poznań"),
//    Trail(4, "Poznań City Run", 3000, 52.4026071, 16.9233213, 52.4118422, 16.9295692, "Run through the city of Poznań, it might not be peaceful, but sometimes you just want to run somewhere busy", "Medium", "Poznań"),
//    Trail(5, "Poznań City Big Run", 19000, 52.3581058, 16.9047782, 52.4891711, 16.9081675, "Test yourself and run through the whole city.", "Hard", "Poznań")
//)
