package raj.on_go.src.network.network_model.oxford.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sense {

    @SerializedName("definitions")
    @Expose
    var definitions: List<String>? = null
    @SerializedName("examples")
    @Expose
    var examples: List<Example>? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("short_definitions")
    @Expose
    var shortDefinitions: List<String>? = null
    @SerializedName("subsenses")
    @Expose
    var subsenses: List<Subsense>? = null
    @SerializedName("thesaurusLinks")
    @Expose
    var thesaurusLinks: List<ThesaurusLink_>? = null
    @SerializedName("notes")
    @Expose
    var notes: List<Note_>? = null

}
