import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDate

data class Movie (
    var name: String,
    var runtime: Int,
    var release: LocalDate,
    var audienceScore: Double,
    var hasOscar: Boolean,
)