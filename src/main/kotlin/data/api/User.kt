package data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
)
