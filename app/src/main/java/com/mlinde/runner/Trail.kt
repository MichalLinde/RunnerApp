package com.mlinde.runner

data class Trail(
    val id: Int,
    val name: String,
    val length: Int,
    val lat_start: Double,
    val lng_start: Double,
    val lat_end: Double,
    val lng_end: Double,
    val description: String
)

val trails = listOf(
    Trail(1, "Jezioro Maltańskie", 8000, 123.0, 120.0, 100.0, 50.0, "Ładne jezioro" ),
    Trail(2, "Park Cytadela", 3000, 123.0, 120.0, 100.0, 50.0, "Duży park" ),
    Trail(3, "Park Wilsona", 1500, 123.0, 120.0, 100.0, 50.0, "Park z palmiarnią" ),
    Trail(1, "Jezioro Maltańskie", 8000, 123.0, 120.0, 100.0, 50.0, "Ładne jezioro" ),
    Trail(2, "Park Cytadela", 3000, 123.0, 120.0, 100.0, 50.0, "Duży park" ),
    Trail(3, "Park Wilsona", 1500, 123.0, 120.0, 100.0, 50.0, "Park z palmiarnią" ),
    Trail(1, "Jezioro Maltańskie", 8000, 123.0, 120.0, 100.0, 50.0, "Ładne jezioro" ),
    Trail(2, "Park Cytadela", 3000, 123.0, 120.0, 100.0, 50.0, "Duży park" ),
    Trail(3, "Park Wilsona", 1500, 123.0, 120.0, 100.0, 50.0, "Park z palmiarnią" ),
    Trail(1, "Jezioro Maltańskie", 8000, 123.0, 120.0, 100.0, 50.0, "Ładne jezioro" ),
    Trail(2, "Park Cytadela", 3000, 123.0, 120.0, 100.0, 50.0, "Duży park" ),
    Trail(3, "Park Wilsona", 1500, 123.0, 120.0, 100.0, 50.0, "Park z palmiarnią" ),
    Trail(1, "Jezioro Maltańskie", 8000, 123.0, 120.0, 100.0, 50.0, "Ładne jezioro" ),
    Trail(2, "Park Cytadela", 3000, 123.0, 120.0, 100.0, 50.0, "Duży park" ),
    Trail(3, "Park Wilsona", 1500, 123.0, 120.0, 100.0, 50.0, "Park z palmiarnią" ),
    Trail(1, "Jezioro Maltańskie", 8000, 123.0, 120.0, 100.0, 50.0, "Ładne jezioro" ),
    Trail(2, "Park Cytadela", 3000, 123.0, 120.0, 100.0, 50.0, "Duży park" ),
    Trail(3, "Park Wilsona", 1500, 123.0, 120.0, 100.0, 50.0, "Park z palmiarnią" ),
    Trail(1, "Jezioro Maltańskie", 8000, 123.0, 120.0, 100.0, 50.0, "Ładne jezioro" ),
    Trail(2, "Park Cytadela", 3000, 123.0, 120.0, 100.0, 50.0, "Duży park" ),
    Trail(3, "Park Wilsona", 1500, 123.0, 120.0, 100.0, 50.0, "Park z palmiarnią" )
)
