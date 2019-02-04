package raj.on_go.src.network.network_model.oxford.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Entry {

    @SerializedName("etymologies")
    @Expose
    var etymologies: List<String>? = null
    @SerializedName("grammaticalFeatures")
    @Expose
    var grammaticalFeatures: List<GrammaticalFeature>? = null
    @SerializedName("homographNumber")
    @Expose
    var homographNumber: String? = null
    @SerializedName("senses")
    @Expose
    var senses: List<Sense>? = null

}
