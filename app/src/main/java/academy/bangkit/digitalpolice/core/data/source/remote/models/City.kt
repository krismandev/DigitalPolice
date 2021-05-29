package academy.bangkit.digitalpolice.core.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class City(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("city_name")
    val cityName: String
)