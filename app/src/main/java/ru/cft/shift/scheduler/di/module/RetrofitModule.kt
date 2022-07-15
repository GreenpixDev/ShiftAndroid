package ru.cft.shift.scheduler.di.module

import dagger.Module
import dagger.Provides
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cft.shift.scheduler.repository.AuthRepository
import ru.cft.shift.scheduler.repository.EventRepository
import ru.cft.shift.scheduler.service.SessionCookieJar
import javax.inject.Singleton

@Module
class RetrofitModule {

    companion object {
        const val DOMAIN = "plannerrestapi.herokuapp.com"
        const val URL = "http://$DOMAIN/"
    }

    @Singleton
    @Provides
    fun provideCookieJar(): SessionCookieJar = SessionCookieJar()

    @Singleton
    @Provides
    fun provideRetrofit(cookieJar: SessionCookieJar): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder()
                .cookieJar(cookieJar)
                .build()
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthRepository(retrofit: Retrofit): AuthRepository {
        return retrofit.create(AuthRepository::class.java)
    }

    @Singleton
    @Provides
    fun provideEventRepository(retrofit: Retrofit): EventRepository {
        return retrofit.create(EventRepository::class.java)
    }
}