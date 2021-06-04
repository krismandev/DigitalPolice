package academy.bangkit.digitalpolice.core.data.source.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeviceToken(
    @field:SerializedName("token")
    @Expose
    val token: String
)