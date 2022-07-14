package ru.cft.shift.scheduler.di.component

import dagger.Component
import ru.cft.shift.scheduler.di.module.MvpPresenterModule
import ru.cft.shift.scheduler.di.module.RetrofitModule
import ru.cft.shift.scheduler.ui.calendar.CalendarActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitModule::class,
    MvpPresenterModule::class
])
interface ApplicationComponent {

    fun inject(activity: CalendarActivity)

}