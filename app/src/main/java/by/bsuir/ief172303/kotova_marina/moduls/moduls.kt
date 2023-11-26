package by.bsuir.ief172303.kotova_marina.moduls

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.viewmodel.dsl.viewModel
import androidx.room.Room
import by.bsuir.ief172303.kotova_marina.repository.MovieRepository
import by.bsuir.ief172303.kotova_marina.repository.database.AppDatabase
import by.bsuir.ief172303.kotova_marina.repository.database.FavoriteMovieDao
import by.bsuir.ief172303.kotova_marina.viewModel.MovieViewModel
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
                AppDatabase::class.java,
                "app_database"
            ).build()
        }

        single { get<AppDatabase>().favoriteMovieDao() }
        //single { get<AppDatabase>() }

       // single { SavedStateHandle() }

        single { MovieRepository() }



        viewModel {
            MovieViewModel(get(),get(),get())
        }
    }



