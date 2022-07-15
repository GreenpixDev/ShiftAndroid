package ru.cft.shift.scheduler.di.component

import dagger.Component
import okhttp3.CookieJar
import ru.cft.shift.scheduler.di.module.MvpPresenterModule
import ru.cft.shift.scheduler.di.module.RetrofitModule
import ru.cft.shift.scheduler.service.SessionCookieJar
import ru.cft.shift.scheduler.ui.calendar.CalendarActivity
import ru.cft.shift.scheduler.ui.calendar.event.modal.EventModalFragment
import ru.cft.shift.scheduler.ui.calendar.settings.SettingsFragment
import ru.cft.shift.scheduler.ui.event.EventActivity
import ru.cft.shift.scheduler.ui.login.LoginActivity
import ru.cft.shift.scheduler.ui.register.RegistrationActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitModule::class,
    MvpPresenterModule::class
])
interface AppComponent {

    val cookieJar: SessionCookieJar

    fun inject(activity: LoginActivity)

    fun inject(activity: RegistrationActivity)

    fun inject(activity: CalendarActivity)

    fun inject(activity: EventActivity)

    fun inject(fragment: EventModalFragment)

    fun inject(fragment: SettingsFragment)

}