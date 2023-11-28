package by.bsuir.ief172303.kotova_marina

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.bsuir.ief172303.kotova_marina.ui.theme.MovieWishListTheme
import by.bsuir.ief172303.kotova_marina.viewModel.MovieViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MovieViewModel = koinViewModel()

            val navController = rememberNavController()

            MovieWishListTheme {
                NavHost(
                    navController = navController,
                    startDestination = "Home"
                ) {
                    composable("Home") {
                        Home(
                            onFavoritesClick = {navController.navigate("Favorites")},
                            onMoviesClick = {navController.navigate("Movies")},
                            onClick = {navController.navigate("About")}
                        )
                    }

                    composable("About"){
                        About{
                            navController.navigate("Home")
                        }
                    }


                    composable(
                        "MovieDetails/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getInt("movieId")
                        movieId?.let {
                            MovieDetails(
                                movieId = it,
                                onBackClick = { navController.popBackStack() },
                                viewModel = viewModel
                            )
                        }
                    }

                    composable("Movies") {
                        Movies(
                            onMovieClick = { movieId ->
                                navController.navigate("MovieDetails/$movieId")
                            },
                            onBackClick = { navController.popBackStack() },
                            viewModel = viewModel
                        )
                    }

                    composable("Favorites") {
                        Favorites(
                            onFavoriteMovieClick = { favoriteMovieId ->
                                navController.navigate("FavoriteMovieDetails/$favoriteMovieId")
                            },
                            onBackClick = { navController.popBackStack() },
                            viewModel = viewModel
                        )
                    }

                    composable(
                        "FavoriteMovieDetails/{favoriteMovieId}",
                        arguments = listOf(navArgument("favoriteMovieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val favoriteMovieId = backStackEntry.arguments?.getInt("favoriteMovieId")
                        favoriteMovieId?.let { fMovieId ->
                            FavoriteMovieDetails(
                                f_movie_id = fMovieId,
                                onBackClick = { navController.popBackStack() },
                                viewModel = viewModel
                            )
                        }
                    }


                }
            }
        }
    }
}