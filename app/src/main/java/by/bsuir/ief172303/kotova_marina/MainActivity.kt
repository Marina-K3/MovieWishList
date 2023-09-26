package by.bsuir.ief172303.kotova_marina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import by.bsuir.ief172303.kotova_marina.models.MovieViewModel
import by.bsuir.ief172303.kotova_marina.ui.theme.MovieWishListTheme

class MainActivity : ComponentActivity() {

    private val movieViewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MovieWishListTheme {
                  NavHost(
                      navController = navController,
                      startDestination = "Home") {

                      composable("Home"){
                              Home(
                                  onClick = { navController.navigate("About") },
                                  movieViewModel = movieViewModel
                              )
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
