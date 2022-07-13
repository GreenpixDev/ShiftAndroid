package ru.cft.shift.scheduler.mvp.ui.calendar

import ru.cft.shift.scheduler.mvp.data.Event
import ru.cft.shift.scheduler.mvp.ui.base.MvpView
import ru.cft.shift.scheduler.mvp.ui.calendar.event.EventMvpPresenter

interface CalendarMvpView : MvpView {

    fun showCalendar(year: Int, month: Int)

    fun showSettingsModalWindow()

    fun showEventModalFragment(presenter: EventMvpPresenter)

    fun hideModalWindow()

    fun clearEventViews()

    fun addEventView(event: Event)

}