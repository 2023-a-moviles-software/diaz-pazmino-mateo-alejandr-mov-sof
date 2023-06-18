

data class Genre(
    var name:String,
    var averangeRating: Double,
    var averangeDuration: Int,
    var featuredDirector: String,
    var movies: ArrayList<Movie>
)