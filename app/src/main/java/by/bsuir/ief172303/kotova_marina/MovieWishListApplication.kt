package by.bsuir.ief172303.kotova_marina

import android.app.Application
import by.bsuir.ief172303.kotova_marina.moduls.favoriteMovieDaoModule
import by.bsuir.ief172303.kotova_marina.moduls.movieRepositoryModule
import by.bsuir.ief172303.kotova_marina.moduls.savedStateHandleModule
import by.bsuir.ief172303.kotova_marina.moduls.viewModelModule
import by.bsuir.ief172303.kotova_marina.repository.database.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

val appModules = listOf(
    savedStateHandleModule,
    movieRepositoryModule,
    favoriteMovieDaoModule,
    viewModelModule,
    databaseModule
)

class MovieWishListApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Инициализируйте здесь вашу логику, например, запустите Коин
       startKoin {
           androidContext(this@MovieWishListApplication)
          modules(viewModelModule)
      }
    }
}
