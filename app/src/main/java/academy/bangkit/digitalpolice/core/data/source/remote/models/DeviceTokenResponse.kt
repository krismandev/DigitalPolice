package academy.bangkit.digitalpolice.core.data.source.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeviceTokenResponse(
    @field:SerializedName("status")
    val status: String
)