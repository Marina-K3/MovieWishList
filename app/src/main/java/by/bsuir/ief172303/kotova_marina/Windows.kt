package by.bsuir.ief172303.kotova_marina

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.bsuir.ief172303.kotova_marina.models.Movie
import by.bsuir.ief172303.kotova_marina.models.MovieViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun About(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()

            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp),

        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(R.mipmap.myphoto),
                    contentDescription = "App Icon",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = "Котова Марина (172303)",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp),
                )

                Text(
                    text = "Версия приложения: 1.0",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {


            Text(
                text = "MovieWishList",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "Приложение-список фильмов на просмотр. " +
                        "Любимые фильмы. Собственные заметки о фильмах. " +
                        "Просмотр жанров, фильтрация по жанрам.",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1)),
                onClick = onClick,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Home",
                    color = Color.White,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Composable
fun Home(onClick: () -> Unit, movieViewModel: MovieViewModel) {
    
    val movies: List<Movie> by movieViewModel.movies.observeAsState(emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(shape = CircleShape)
        ) {
            Image(
                painter = painterResource(R.mipmap.logo),
                contentDescription = "App Icon",
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = "Добро пожаловать в MovieWishList!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1)),
            onClick = onClick,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(
                text = "About",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1)),
            onClick = { /* Добавить новый фильм */ },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Добавить фильм",
                color = Color.White,
                fontSize = 16.sp,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            movies.forEach { movie ->
                MovieItem(
                    movie = movie,
                    onDelete = { movieViewModel.deleteMovie(movie) },
                    onEdit = { movieViewModel.deleteMovie(movie) }
                )
            }
        }
    }
}
