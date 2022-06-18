package com.mlinde.runner

import java.io.Serializable

data class Stat(
    val id: Int? = null,
    val time: Int? = null,
    val trail_id: Int? = null,
    val user_id: Int? = null
) : Serializable

//val stats = listOf(
//    Stat(1, 192846, 1, 2),
//    Stat(2, 879265, 1, 2),
//    Stat(3, 12345, 1, 4),
//    Stat(4, 189385, 1, 2)
//)
