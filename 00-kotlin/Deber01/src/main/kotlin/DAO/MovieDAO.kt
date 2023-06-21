package DAO

import Entities.Movie
import Entities.Genre

object MovieDAO {
    fun create(movie: Movie, genre: Genre): Genre {
        genre.movies?.add(movie)
        return genre
    }
    fun delete(genres: ArrayList<Genre>, movieId:Int):ArrayList<Genre> {
        var indexMovie:Int = -1
        var indexGenre:Int = -1
        genres.forEachIndexed { index:Int, genre ->
            var indexObtained = genre.movies?.let { getIndexById(movieId, it) }!!
            if (indexObtained>=0) {
                indexMovie = indexObtained
                indexGenre = index
            }
        }
        if (indexMovie>=0 && indexGenre>=0){
            genres[indexGenre].movies?.removeAt(indexMovie)
        }
        return genres
    }
    fun getAll(genres:ArrayList<Genre>):ArrayList<Movie>{
        var movies:ArrayList<Movie> = ArrayList()
        genres.forEach {
            it.movies?.forEach { movies.add(it) }
        }
        return movies
    }
    fun update(genres:ArrayList<Genre>, movieId:Int, movieUpdated: Movie):ArrayList<Genre> {
        var indexMovie:Int = -1
        var indexGenre:Int = -1
        genres.forEachIndexed { index:Int, genre ->
            var indexObtained = genre.movies?.let { getIndexById(movieId, it) }!!
            if (indexObtained>=0) {
                indexMovie = indexObtained
                indexGenre = index
            }
        }
        if (indexMovie>=0 && indexGenre>=0){
            genres[indexGenre].movies?.set(indexMovie,movieUpdated)
        }
        return genres
    }

    private fun getIndexById(movieId:Int, movies:ArrayList<Movie>):Int{
        movies.forEachIndexed { index:Int, movie: Movie ->
            if (movie.movieId==movieId)
                return index
        }
        return -1
    }
}