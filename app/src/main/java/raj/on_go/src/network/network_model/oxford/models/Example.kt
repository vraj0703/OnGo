package raj.on_go.src.network.network_model.oxford.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {

    @SerializedName("text")
    @Expose
    var text: String? = null

    override fun toString(): String {
        return text ?: ""
    }

}
