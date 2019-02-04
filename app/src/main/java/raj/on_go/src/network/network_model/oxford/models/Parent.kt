package raj.on_go.src.network.network_model.oxford.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import raj.on_go.src.network.network_model.oxford.Meaning

class Parent {

    @SerializedName("metadata")
    @Expose
    var metadata: Metadata? = null
    @SerializedName("results")
    @Expose
    var results: List<Meaning>? = null

}
