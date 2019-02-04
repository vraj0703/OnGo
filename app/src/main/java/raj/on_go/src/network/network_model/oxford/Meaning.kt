package raj.on_go.src.network.network_model.oxford

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import raj.on_go.src.network.network_model.oxford.models.LexicalEntry

class Meaning {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
    @SerializedName("lexicalEntries")
    @Expose
    var lexicalEntries: List<LexicalEntry>? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("word")
    @Expose
    var word: String? = null

}
