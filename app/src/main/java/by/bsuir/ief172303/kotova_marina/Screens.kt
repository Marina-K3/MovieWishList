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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import by.bsuir.ief172303.kotova_marina.model.FavoriteMovie
import by.bsuir.ief172303.kotova_marina.viewModel.MovieViewModel
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation

@Composable
fun About(onClick: () -> Unit) {
    val customFontFamily = FontFamily(Font(R.font.reza_zulmi_sans))

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
                    text = stringResource(id = R.string.about_me),
                    fontSize = 14.sp,
                    fontFamily = customFontFamily,
                    color = Color.White,
                    modifier = Modifier.padding(10.dp),
                )

                Text(
                    text = stringResource(id = R.string.app_version),
                    fontSize = 14.sp,
                    fontFamily = customFontFamily,
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
                text = stringResource(id = R.string.app_name),
                fontFamily = customFontFamily,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.about_app),
                fontSize = 14.sp,
                fontFamily = customFontFamily,
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
                    text = stringResource(id = R.string.but_back),
                    fontFamily = customFontFamily,
                    color = Color.White,
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Composable
fun Home(onFavoritesClick: () -> Unit, onMoviesClick: () -> Unit, onClick: () -> Unit) {

    val customFontFamily = FontFamily(Font(R.font.reza_zulmi_sans))
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
            text = stringResource(id = R.string.greeting),
            fontFamily = customFontFamily,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
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
                text = stringResource(id = R.string.but_about),
                fontFamily = customFontFamily,
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
                text = stringResource(id = R.string.but_fav),
                fontFamily = customFontFamily,
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
                text = stringResource(id = R.string.but_movies),
                fontFamily = customFontFamily,
                color = Color.White,
                fontSize = 16.sp,
            )
        }


    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    val customFontFamily = FontFamily(Font(R.font.reza_zulmi_sans))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
            .shadow(4.dp, shape = RoundedCornerShape(4.dp))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),

        ) {
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(4.dp))
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
                Text(text = movie.name, fontWeight = FontWeight.Bold,  fontFamily = customFontFamily,color = Color.White)
                Text(text = stringResource(id = R.string.year) + movie.year, fontFamily = customFontFamily,color = Color.White)
                Text(text = stringResource(id = R.string.rating) + movie.rating, fontFamily = customFontFamily,color = Color.White)
            }
        }
    }
}

@Composable
fun MovieDetails(
    movieId: Int,
    onBackClick: () -> Unit,
    viewModel: MovieViewModel
)
{
    val movieState by viewModel.movies.observeAsState(emptyList())
    val movie = movieState.find { it.id == movieId }
    val customFontFamily = FontFamily(Font(R.font.reza_zulmi_sans))

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
                            .clip(RoundedCornerShape(4.dp))
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
                            Text(text = movie.name, fontWeight = FontWeight.Bold, fontFamily = customFontFamily, color = Color.White)
                            Text(text = stringResource(id = R.string.year) + movie.year, fontFamily = customFontFamily, color = Color.White)
                            Text(text = stringResource(id = R.string.rating) + movie.rating, fontFamily = customFontFamily, color = Color.White)
                            Text(text = stringResource(id = R.string.directors) + movie.directors, fontFamily = customFontFamily, color = Color.White)
                        }
                    }

                    Text(text = stringResource(id = R.string.description) + movie.description, fontFamily = customFontFamily, color = Color.White)

                    // Кнопка для добавления фильма в избранное
                    Button(
                        onClick = { viewModel.addToFavorites(movie) },
                        modifier = Modifier.padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFBC0DC1))
                    ) {
                        Text(text = stringResource(id = R.string.but_add_fav), fontFamily = customFontFamily, color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onBackClick,
                    modifier = Modifier.padding(bottom = 16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1))
                ) {
                    Text(text = stringResource(id = R.string.but_back), fontFamily = customFontFamily, color = Color.White)
                }
            }
        }
    }
}


@Composable
fun Movies(
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    viewModel: MovieViewModel
) {
    val moviesState by viewModel.movies.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val customFontFamily = FontFamily(Font(R.font.reza_zulmi_sans))

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
            Text(text = stringResource(id = R.string.but_back), fontFamily = customFontFamily, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(60.dp),
                color = Color(0xFFBC0DC1)
            )
        } else {
            LazyColumn {
                items(moviesState) { movie ->
                    MovieItem(movie = movie, onClick = { onMovieClick(movie.id) })
                }
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
    val customFontFamily = FontFamily(Font(R.font.reza_zulmi_sans))

    val favoriteMovies = favoriteMoviesState.map { favoriteMovieEntity ->
        FavoriteMovie(
            movie = favoriteMovieEntity.movie,
            rating = favoriteMovieEntity.rating,
            comment = favoriteMovieEntity.comment
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        if (favoriteMoviesState.isEmpty()) {
            Text(text = "Список избранных фильмов пуст", fontFamily = customFontFamily, color = Color.White)
        } else {
            LazyColumn {
                items(favoriteMovies) { f_movie ->
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
            colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1))
        ) {
            Text(text = stringResource(id = R.string.but_back), fontFamily = customFontFamily, color = Color.White)
        }
    }
}

@Composable
fun FavoriteMovieItem(f_movie: FavoriteMovie, onFavoriteMovieClick: (Int) -> Unit) {
    val customFontFamily = FontFamily(Font(R.font.reza_zulmi_sans))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onFavoriteMovieClick(f_movie.movie.id) }
            .padding(16.dp)
            .shadow(4.dp, shape = RoundedCornerShape(4.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()

                .padding(bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(4.dp))
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
            }
            Column(Modifier.padding(start = 16.dp)) {
                Text(text = f_movie.movie.name, fontWeight = FontWeight.Bold,  fontFamily = customFontFamily,color = Color.White)
                Text(text = stringResource(id = R.string.year) + f_movie.movie.year, fontFamily = customFontFamily,color = Color.White)
                Text(text = stringResource(id = R.string.rating) + f_movie.movie.rating, fontFamily = customFontFamily,color = Color.White)
                Text(text = stringResource(id = R.string.directors) + f_movie.movie.directors, fontFamily = customFontFamily,color = Color.White)
            }
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
    val customFontFamily = FontFamily(Font(R.font.reza_zulmi_sans))


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
                        .clip(RoundedCornerShape(4.dp))
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
                        Text(text = f_movie?.movie?.name.toString(), fontWeight = FontWeight.Bold, fontFamily = customFontFamily, color = Color.White)
                        Text(text = stringResource(id = R.string.year) + f_movie?.movie?.year, fontFamily = customFontFamily, color = Color.White)
                        Text(text = stringResource(id = R.string.rating) + f_movie?.movie?.rating, fontFamily = customFontFamily, color = Color.White)
                        Text(text = stringResource(id = R.string.directors) + f_movie?.movie?.directors, fontFamily = customFontFamily, color = Color.White)
                    }
                }

                Text(text = "Описание: ${f_movie?.movie?.description}", fontFamily = customFontFamily, color = Color.White)

                // Рецензия на фильм
                TextField(
                    value = reviewText,
                    onValueChange = { reviewText = it },
                    label = { Text(text = "Рецензия", fontFamily = customFontFamily, color = Color.White) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                // Рейтинг фильма
                Text(
                    text = "Оценка фильма: %.1f".format(rating),
                    modifier = Modifier.padding(top = 16.dp),
                    fontFamily = customFontFamily, color = Color.White
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
                    Text(text = stringResource(id = R.string.but_send_mess), fontFamily = customFontFamily, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Кнопка для удаления фильма из избранного
                Button(
                    onClick = { f_movie?.let { viewModel.removeFromFavorites(it) } },
                    modifier = Modifier.padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFBC0DC1))
                ) {
                    Text(text = stringResource(id = R.string.but_delete_movie), fontFamily = customFontFamily, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onBackClick,
                    modifier = Modifier.padding(bottom = 36.dp),
                    colors = ButtonDefaults.outlinedButtonColors(Color(0xFFBC0DC1)),
                ) {
                    Text(text = stringResource(id = R.string.but_back), fontFamily = customFontFamily, color = Color.White)
                }
            }
        }
    }
}
