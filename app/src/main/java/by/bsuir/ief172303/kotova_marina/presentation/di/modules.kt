package by.bsuir.ief172303.kotova_marina.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import androidx.room.Room
import by.bsuir.ief172303.kotova_marina.repository.MovieRepository
import by.bsuir.ief172303.kotova_marina.presentation.viewModel.MovieViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val savedStateHandleModule = module {

}

val movieRepositoryModule = module {

}

val favoriteMovieDaoModule = module {

}



    val viewModelModule = module {

        single {
            Room.databaseBuilder(
                androidApplication(),
                by.bsuir.ief172303.kotova_marina.data.database.AppDatabase::class.java,
                "app_database"
            ).build()
        }

        single { get<by.bsuir.ief172303.kotova_marina.data.database.AppDatabase>().favoriteMovieDao() }


        single { MovieRepository() }



        viewModel {
            MovieViewModel(get(),get(),get())
        }
    }



