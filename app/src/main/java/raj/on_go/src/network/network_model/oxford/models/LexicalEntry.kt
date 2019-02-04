package raj.on_go.src.network.network_model.oxford.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LexicalEntry {

    @SerializedName("entries")
    @Expose
    var entries: List<Entry>? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
    @SerializedName("lexicalCategory")
    @Expose
    var lexicalCategory: String? = null
    @SerializedName("pronunciations")
    @Expose
    var pronunciations: List<Pronunciation>? = null
    @SerializedName("text")
    @Expose
    var text: String? = null

}
