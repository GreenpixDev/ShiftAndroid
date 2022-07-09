package ru.cft.shift.scheduler.mvp.ui.calendar

import ru.cft.shift.scheduler.mvp.ui.base.MvpView

interface CalendarMvpView : MvpView {

    fun showCalendar(year: Int, month: Int)

    fun showSettingsFragment()

}