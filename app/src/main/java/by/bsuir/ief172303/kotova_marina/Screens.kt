package by.bsuir.ief172303.kotova_marina

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import by.bsuir.ief172303.kotova_marina.model.Movie
import by.bsuir.ief172303.kotova_marina.repository.MovieRepository
import androidx.compose.runtime.setValue
import by.bsuir.ief172303.kotova_marina.viewModel.MovieViewModel

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
fun Home(onFavoritesClick: () -> Unit, onMoviesClick: () -> Unit, onClick: () -> Unit) {


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

        Button(
            colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1)),
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "About",
                color = Color.White,
                fontSize = 16.sp,
            )
        }

        Button(
            colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1)),
            onClick = onFavoritesClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Избранное",
                color = Color.White,
                fontSize = 16.sp,
            )
        }
        Button(
            colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1)),
            onClick = onMoviesClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Фильмы",
                color = Color.White,
                fontSize = 16.sp,
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
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Text(text = movie.name, fontWeight = FontWeight.Bold)
            Text(text = "Год: ${movie.year}")
        }
    }
}

@Composable
fun MovieDetails(movieId: Int, onBackClick: () -> Unit, viewModel: MovieViewModel) {
    val movieState by viewModel.movies.observeAsState(emptyList())
    val movie = movieState.find { it.id == movieId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        movie?.let { movie ->
            Text(text = movie.name, fontWeight = FontWeight.Bold)
            Text(text = "Год: ${movie.year}")
            Text(text = "Описание: ${movie.description}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Назад")
        }
    }
}

@Composable
fun Movies(onMovieClick: (Int) -> Unit, onBackClick: () -> Unit, viewModel: MovieViewModel) {
    val moviesState by viewModel.movies.observeAsState(emptyList())

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn {
        items(moviesState) { movie ->
            MovieItem(movie = movie, onClick = { onMovieClick(movie.id) })
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = onBackClick,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Text(text = "Назад")
    }
}

