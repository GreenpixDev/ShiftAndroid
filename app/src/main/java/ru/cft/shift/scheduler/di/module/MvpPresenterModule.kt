package ru.cft.shift.scheduler.di.module

import dagger.Binds
import dagger.Module
import ru.cft.shift.scheduler.ui.calendar.CalendarMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.CalendarPresenter

@Module
abstract class MvpPresenterModule {

    @Binds
    abstract fun bindCalendarPresenter(presenter: CalendarPresenter): CalendarMvpPresenter

}