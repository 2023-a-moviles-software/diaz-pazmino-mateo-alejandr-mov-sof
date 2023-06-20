package DAO

import Entities.Movie
import Entities.Genre

object MovieDAO {
    fun create(movie: Movie, genre: Genre): Genre {
        genre.movies?.add(movie)
        return genre
    }
    fun delete(genre: Genre, movieId:Int): Genre {
        var index = genre.movies?.let { getIndexById(movieId, it) }
        if (index != null) {
            genre.movies?.removeAt(index)
        }
        return genre
    }
    fun getByGenre(genre: Genre): ArrayList<Movie>? {
        return genre.movies
    }
    fun getAll(genres:ArrayList<Genre>):ArrayList<Movie>{
        var movies:ArrayList<Movie> = ArrayList()
        genres.forEach {
            it.movies?.forEach { movies.add(it) }
        }
        return movies
    }
    fun update(genre: Genre, movieId:Int, movieUpdated: Movie): Genre {
        var index = genre.movies?.let { getIndexById(movieId, it) }
        if (index != null) {
            genre.movies?.set(index,movieUpdated)
        }
        return genre
    }

    private fun getIndexById(movieId:Int, movies:ArrayList<Movie>):Int{
        movies.forEachIndexed { index:Int, movie: Movie ->
            if (movie.movieId==movieId)
                return index
        }
        return -1
    }
}