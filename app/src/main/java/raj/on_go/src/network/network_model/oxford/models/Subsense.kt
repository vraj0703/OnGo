package raj.on_go.src.network.network_model.oxford.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Subsense {

    @SerializedName("definitions")
    @Expose
    var definitions: List<String>? = null
    @SerializedName("examples")
    @Expose
    var examples: List<Example_>? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("short_definitions")
    @Expose
    var shortDefinitions: List<String>? = null
    @SerializedName("thesaurusLinks")
    @Expose
    var thesaurusLinks: List<ThesaurusLink>? = null
    @SerializedName("notes")
    @Expose
    var notes: List<Note>? = null
    @SerializedName("domains")
    @Expose
    var domains: List<String>? = null

}
