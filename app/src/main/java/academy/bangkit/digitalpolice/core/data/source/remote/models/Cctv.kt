package academy.bangkit.digitalpolice.core.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class Cctv(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("latitude")
    val latitude: String,

    @field:SerializedName("longitude")
    val longitude: String,

    @field:SerializedName("city")
    val city: City
)