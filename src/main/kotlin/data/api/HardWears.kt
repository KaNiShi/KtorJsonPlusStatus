package data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class HardWear

@Serializable
data class Cpu(
    @SerialName("name")
    val name: String = "",
    @SerialName("core")
    val core: Int = 0,
    @SerialName("thread")
    val thread: Int = 0,
    @SerialName("speed")
    val speed: Double = 0.0,
    @SerialName("tdp")
    val tdp: Int = 0,
) : HardWear()

@Serializable
data class Storage(
    @SerialName("maker")
    val maker: String = "",
    @SerialName("capacity")
    val capacity: Long = 0,
    @SerialName("type")
    val type: Type = Type.UNKNOWN,
) : HardWear() {
    @Serializable
    enum class Type {
        SSD,
        HDD,
        SSHD,
        NVM_EXPRESS,
        UNKNOWN
    }
}
