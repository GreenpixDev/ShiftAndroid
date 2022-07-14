package ru.cft.shift.scheduler.di.component

import dagger.Component
import ru.cft.shift.scheduler.di.module.MvpPresenterModule
import ru.cft.shift.scheduler.di.module.RetrofitModule
import ru.cft.shift.scheduler.ui.calendar.CalendarActivity
import ru.cft.shift.scheduler.ui.calendar.event.modal.EventModalFragment
import ru.cft.shift.scheduler.ui.calendar.settings.SettingsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitModule::class,
    MvpPresenterModule::class
])
interface AppComponent {

    fun inject(activity: CalendarActivity)

    fun inject(fragment: EventModalFragment)

    fun inject(fragment: SettingsFragment)

}