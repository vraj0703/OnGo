package raj.on_go.src.network


import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import raj.on_go.src.network.network_model.oxford.Meaning
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {
    private const val OxfordDictionaryBseUrl = "https://od-api.oxforddictionaries.com/api/v1/entries/en/"
    private const val GoogleFormFeedbackUrl = "https://docs.google.com/forms/d/1P9Yjz6OAZPxbA1m28hIzdySLPL_5QMJ-4NwpLKFLzi8/"


    fun makeService(): ApiInterface {
        return Retrofit.Builder()
                .baseUrl(OxfordDictionaryBseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(ApiInterface::class.java)
    }

    fun makeFeedbackService(): ApiInterface {
        return Retrofit.Builder()
                .baseUrl(GoogleFormFeedbackUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(ApiInterface::class.java)
    }
}

class ApiHelper {
    suspend fun search(word: CharSequence?): Meaning? {
        val service = ApiService.makeService()
        val request = service.meaningAsync(word.toString())
        try {
            val response = request.await()
            Log.e("word", response.results!![0].word)
            return response.results!![0]
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return null
    }

}