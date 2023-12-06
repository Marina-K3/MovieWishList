package by.bsuir.ief172303.kotova_marina.presentation

import android.app.Application
import by.bsuir.ief172303.kotova_marina.presentation.di.favoriteMovieDaoModule
import by.bsuir.ief172303.kotova_marina.presentation.di.movieRepositoryModule
import by.bsuir.ief172303.kotova_marina.presentation.di.savedStateHandleModule
import by.bsuir.ief172303.kotova_marina.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

val appModules = listOf(
    savedStateHandleModule,
    movieRepositoryModule,
    favoriteMovieDaoModule,
    viewModelModule
)

class MovieWishListApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Инициализируйте здесь вашу логику, например, запустите Коин
       startKoin {
           androidContext(this@MovieWishListApplication)
          modules(appModules)
      }
    }
}
