package by.bsuir.ief172303.kotova_marina

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MovieWishListApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Инициализируйте здесь вашу логику, например, запустите Коин
        startKoin {
            androidContext(applicationContext)
            modules()
        }
    }
}
