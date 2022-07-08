package ru.cft.shift.scheduler.mvp.ui.calendar

import ru.cft.shift.scheduler.mvp.ui.base.MvpView
import java.util.*

interface CalendarMvpView : MvpView {

    fun showCalendar(date: Calendar)

    fun showSettingsFragment()

}