package ru.cft.shift.scheduler.mvp.ui.calendar

import ru.cft.shift.scheduler.mvp.data.Event
import ru.cft.shift.scheduler.mvp.ui.base.MvpView

interface CalendarMvpView : MvpView {

    fun showCalendar(year: Int, month: Int)

    fun showSettingsModalWindow()

    fun hideModalWindow()

    fun clearEventViews()

    fun addEventView(event: Event)

}