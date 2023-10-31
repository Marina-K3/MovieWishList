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
import androidx.compose.material3.Slider
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.runtime.setValue
import by.bsuir.ief172303.kotova_marina.viewModel.MovieViewModel
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation

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
                    text = "Назад",
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
                text = "О приложении",
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
        Row(
            modifier = Modifier.padding(16.dp),

        ) {
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .padding(10.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = movie.posterURL,
                        builder = {
                            transformations(RoundedCornersTransformation(4f))
                        }
                    ),
                    contentDescription = "Movie Poster"
                )
            }
            Column {
                Text(text = movie.name, fontWeight = FontWeight.Bold)
                Text(text = "Год: ${movie.year}")
                Text(text = "Рейтинг: ${movie.rating}")
            }
        }
    }
}

@Composable
fun MovieDetails(
    movieId: Int,
    onBackClick: () -> Unit,
    viewModel: MovieViewModel
) {
    val movieState by viewModel.movies.observeAsState(emptyList())
    val movie = movieState.find { it.id == movieId }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                movie?.let { movie ->
                    Box(
                        modifier = Modifier
                            .size(300.dp)
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = movie.posterURL,
                                builder = {
                                    transformations(RoundedCornersTransformation(4f))
                                }
                            ),
                            contentDescription = "Movie Poster"
                        )
                    }

                    Row(modifier = Modifier.padding(16.dp)) {
                        Column {
                            Text(text = movie.name, fontWeight = FontWeight.Bold)
                            Text(text = "Год: ${movie.year}")
                            Text(text = "Рейтинг: ${movie.rating}")
                            Text(text = "Режиссеры: ${movie.directors}")
                        }
                    }

                    Text(text = "Описание: ${movie.description}")

                    // Кнопка для добавления фильма в избранное
                    Button(
                        onClick = { viewModel.addToFavorites(movie) },
                        modifier = Modifier.padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFBC0DC1))
                    ) {
                        Text(text = "В избранное")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onBackClick,
                    modifier = Modifier.padding(bottom = 16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1))
                ) {
                    Text(text = "Назад")
                }
            }
        }
    }
}

@Composable
fun Movies(onMovieClick: (Int) -> Unit, onBackClick: () -> Unit, viewModel: MovieViewModel) {
    val moviesState by viewModel.movies.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(bottom = 16.dp),
            colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1)),
        ) {
            Text(text = "Назад")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(moviesState) { movie ->
                MovieItem(movie = movie, onClick = { onMovieClick(movie.id) })
            }
        }
    }


}

@Composable
fun Favorites(
    onFavoriteMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    viewModel: MovieViewModel
) {
    val favoriteMoviesState by viewModel.favoriteMovies.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        if (favoriteMoviesState.isEmpty()) {
            Text(text = "Список избранных фильмов пуст")
        } else {
            LazyColumn {
                items(favoriteMoviesState) { f_movie ->
                    FavoriteMovieItem(
                        f_movie = f_movie,
                        onFavoriteMovieClick = onFavoriteMovieClick
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(bottom = 16.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
        ) {
            Text(text = "Назад")
        }
    }
}

@Composable
fun FavoriteMovieItem(f_movie: MovieViewModel.FavoriteMovie, onFavoriteMovieClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onFavoriteMovieClick(f_movie.movie.id) }
            .padding(bottom = 16.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = f_movie.movie.posterURL,
                builder = {
                    transformations(RoundedCornersTransformation(4f))
                }
            ),
            contentDescription = "Movie Poster",
            modifier = Modifier.size(100.dp)
        )
        Column(Modifier.padding(start = 16.dp)) {
            Text(text = f_movie.movie.name, fontWeight = FontWeight.Bold)
            Text(text = "Год: ${f_movie.movie.year}")
            Text(text = "Рейтинг: ${f_movie.movie.rating}")
            Text(text = "Режиссеры: ${f_movie.movie.directors}")
        }
    }
}

@Composable
fun FavoriteMovieDetails(
    f_movie_id: Int,
    onBackClick: () -> Unit,
    viewModel: MovieViewModel
) {

    val movieState by viewModel.favoriteMovies.observeAsState(emptyList())
    val f_movie = movieState.find { it.movie.id == f_movie_id }


    var reviewText by remember { mutableStateOf(f_movie?.comment ?: "") }
    var rating by remember { mutableStateOf(f_movie?.rating ?: 0f) }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(300.dp)
                        .padding(10.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = f_movie?.movie?.posterURL,
                            builder = {
                                transformations(RoundedCornersTransformation(4f))
                            }
                        ),
                        contentDescription = "Movie Poster"
                    )
                }
                Row(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Text(text = f_movie?.movie?.name.toString(), fontWeight = FontWeight.Bold)
                        Text(text = "Год: ${f_movie?.movie?.year}")
                        Text(text = "Рейтинг: ${f_movie?.movie?.rating}")
                        Text(text = "Режиссеры: ${f_movie?.movie?.directors}")
                    }
                }

                Text(text = "Описание: ${f_movie?.movie?.description}")

                // Рецензия на фильм
                TextField(
                    value = reviewText,
                    onValueChange = { reviewText = it },
                    label = { Text(text = "Рецензия") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                // Рейтинг фильма
                Text(
                    text = "Оценка фильма: %.1f".format(rating),
                    modifier = Modifier.padding(top = 16.dp)
                )

                Slider(
                    value = rating,
                    onValueChange = { newRating -> rating = newRating },
                    valueRange = 0f..5f,
                    steps = 0,
                    modifier = Modifier.padding(top = 8.dp)
                )

                // Кнопка для отправки рецензии
                Button(
                    onClick = { f_movie?.let { viewModel.submitReview(it, reviewText, rating) } },
                    modifier = Modifier.padding(top = 6.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFBC0DC1))
                ) {
                    Text(text = "Отправить рецензию")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Кнопка для удаления фильма из избранного
                Button(
                    onClick = { f_movie?.let { viewModel.removeFromFavorites(it) } },
                    modifier = Modifier.padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFBC0DC1))
                ) {
                    Text(text = "Удалить из избранного")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onBackClick,
                    modifier = Modifier.padding(bottom = 36.dp),
                    colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1)),
                ) {
                    Text(text = "Назад")
                }
            }
        }
    }
}
