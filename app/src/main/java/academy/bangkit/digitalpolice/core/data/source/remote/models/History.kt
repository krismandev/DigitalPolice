package academy.bangkit.digitalpolice.core.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class History(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("anomaly_type")
    val anomalyType: String,

    @field:SerializedName("video_link")
    val videoLink: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("cctv")
    val cctv: Cctv
)