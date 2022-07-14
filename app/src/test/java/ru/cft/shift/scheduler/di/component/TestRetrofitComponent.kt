package ru.cft.shift.scheduler.di.component

import dagger.Component
import ru.cft.shift.scheduler.di.module.RetrofitModule
import ru.cft.shift.scheduler.repository.AuthRepositoryTest
import ru.cft.shift.scheduler.repository.EventRepositoryTest
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitModule::class
])
interface TestRetrofitComponent {

    fun inject(test: AuthRepositoryTest)

    fun inject(test: EventRepositoryTest)

}