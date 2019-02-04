package raj.on_go.src.network.network_model.oxford.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Metadata {

    @SerializedName("provider")
    @Expose
    var provider: String? = null

}