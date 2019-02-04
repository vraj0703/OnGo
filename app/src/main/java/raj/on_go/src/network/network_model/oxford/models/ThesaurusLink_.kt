package raj.on_go.src.network.network_model.oxford.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThesaurusLink_ {

    @SerializedName("entry_id")
    @Expose
    var entryId: String? = null
    @SerializedName("sense_id")
    @Expose
    var senseId: String? = null

}
