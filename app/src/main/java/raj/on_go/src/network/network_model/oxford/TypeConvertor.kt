package raj.on_go.src.network.network_model.oxford

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Converters {
    @TypeConverter
    fun fromString(value: String?): Meaning {
        val listType = object : TypeToken<Meaning>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(Meaning: Meaning?): String {
        val gson = Gson()
        return gson.toJson(Meaning)
    }
}