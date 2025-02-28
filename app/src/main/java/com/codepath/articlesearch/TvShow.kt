import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TvShow(
    @SerializedName("original_name")
    val name : String?,
    @SerializedName("overview")
    val showDescription : String?,
    @SerializedName("vote_average")
    val ratingScore : Double?,
    @SerializedName("poster_path")
    val imageUrl : String?,
    @SerializedName("vote_count")
    val voteCount : Double?,
    @SerializedName("first_air_date")
    val firstAirDate : String?


) : java.io.Serializable