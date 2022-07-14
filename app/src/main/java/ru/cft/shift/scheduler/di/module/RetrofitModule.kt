package ru.cft.shift.scheduler.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cft.shift.scheduler.repository.AuthRepository
import ru.cft.shift.scheduler.repository.EventRepository

@Module
class RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://plannerrestapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAuthRepository(retrofit: Retrofit): AuthRepository {
        return retrofit.create(AuthRepository::class.java)
    }

    @Provides
    fun provideEventRepository(retrofit: Retrofit): EventRepository {
        return retrofit.create(EventRepository::class.java)
    }
}