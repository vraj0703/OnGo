package raj.on_go.src.network


import kotlinx.coroutines.Deferred
import raj.on_go.src.network.network_model.oxford.models.Parent
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {


    @Headers("app_id: c21bdff2", "app_key: ca80b35767fa4a3d1a0ee2f41838d1f3")
    @GET("{word}/regions=us")
    fun meaningAsync(@Path("word") word: String): Deferred<Parent>

    @GET("formResponse")
    fun sendFeedbackAsync(@Query(nameKey) name: String, @Query(feedbackKey) feedback: String): Deferred<Any>

    companion object {
        const val nameKey = "entry.666555227"
        const val feedbackKey = "entry.1632326946"
    }
}
