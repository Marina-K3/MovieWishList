package by.bsuir.ief172303.kotova_marina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.bsuir.ief172303.kotova_marina.ui.theme.MovieWishListTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MovieWishListTheme {
                NavHost(
                    navController = navController,
                    startDestination = "Home"
                ) {
                    composable("Home") {
                        Home(onMovieClick = {
                                movieId ->
                            navController.navigate("MovieDetails/$movieId")
                        }, onClick = { navController.navigate("About") })
                    }
                    composable(
                        "MovieDetails/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getInt("movieId")
                        movieId?.let {
                            MovieDetails(
                                movieId = it,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                    }
                    composable("About"){
                        About{
                            navController.navigate("Home")
                        }
                    }
                }
            }
        }
    }
}