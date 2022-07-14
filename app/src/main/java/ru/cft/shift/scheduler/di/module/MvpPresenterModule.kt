package ru.cft.shift.scheduler.di.module

import dagger.Binds
import dagger.Module
import ru.cft.shift.scheduler.ui.calendar.CalendarMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.CalendarPresenter
import ru.cft.shift.scheduler.ui.calendar.event.modal.EventModalMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.event.modal.EventModalPresenter
import ru.cft.shift.scheduler.ui.calendar.settings.SettingsMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.settings.SettingsPresenter
import javax.inject.Singleton

@Module
abstract class MvpPresenterModule {

    @Singleton
    @Binds
    abstract fun bindCalendarPresenter(presenter: CalendarPresenter): CalendarMvpPresenter

    @Singleton
    @Binds
    abstract fun bindSettingsPresenter(presenter: SettingsPresenter): SettingsMvpPresenter

    @Singleton
    @Binds
    abstract fun bindEventModalPresenter(presenter: EventModalPresenter): EventModalMvpPresenter

}