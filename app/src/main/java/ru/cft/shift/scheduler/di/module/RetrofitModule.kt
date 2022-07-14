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

    @Singleton
    @Provides
    fun provideCookieJar(): CookieJar = SessionCookieJar()

    @Singleton
    @Provides
    fun provideRetrofit(cookieJar: CookieJar): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://plannerrestapi.herokuapp.com/")
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