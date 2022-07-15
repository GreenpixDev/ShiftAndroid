package ru.cft.shift.scheduler.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cft.shift.scheduler.json.JsonDateConverter
import ru.cft.shift.scheduler.repository.AuthRepository
import ru.cft.shift.scheduler.repository.EventRepository
import ru.cft.shift.scheduler.service.SessionCookieJar
import java.util.*
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
    fun provideGson(): Gson {
        return GsonBuilder()
            .disableHtmlEscaping()
            .registerTypeAdapter(Date::class.java, JsonDateConverter())
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(cookieJar: SessionCookieJar, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
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