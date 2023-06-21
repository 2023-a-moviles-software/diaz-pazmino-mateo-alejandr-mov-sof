import Controllers.GenreController
import DAO.GenreDAO
import DAO.MovieDAO
import java.util.Scanner


fun main(args: Array<String>) {
    var option = 10
    val input = Scanner(System.`in`)
    var genreController: GenreController = GenreController()
    do {
        println("MENU\n1.Mostrar generos y películas\n2.Mostrar generos\n3.Mostrar películas\n4.Agregar Genero\n" +
                "5.Agregar Película\n6.Editar genero\n7.Editar película\n8.Eliminar Genero\n9.Eliminar película\n" +
                "10.Salir")
        print("Ingrese una opción: ")
        option = input.nextInt()
        when(option){
            1 -> genreController.showAllGenres()
            2 -> genreController.showOnlyGenres()
            3 -> genreController.showMoviesByGenre()
            4 -> genreController.createGenre()
            5 -> genreController.addMovieToGenre()
            6 -> genreController.editGenre()
            7 -> genreController.editMovies()
            8 -> genreController.deleteGenre()
            9 -> genreController.deleteMovie()
            10 -> continue
            else -> println("Ingresa un número del 1 al 10")
        }
        if(option!=10){
            print("Presiona ENTER para continuar")
            var wait = Scanner(System.`in`).nextLine()
        }
    }while (option!=10)

}
