package ru.cft.shift.scheduler.ui.calendar

import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.ui.base.MvpView
import ru.cft.shift.scheduler.ui.calendar.event.EventMvpPresenter

interface CalendarMvpView : MvpView {

    fun reloadCalendar()

    fun showCalendar(year: Int, month: Int)

    fun showSettingsModalWindow()

    fun showEventModalFragment(presenter: EventMvpPresenter)

    fun hideModalWindow()

    fun clearEventViews()

    fun addEventView(event: Event)

}