package ru.cft.shift.scheduler.ui.calendar.week

import ru.cft.shift.scheduler.ui.base.MvpView
import ru.cft.shift.scheduler.ui.calendar.day.DayMvpPresenter
import java.util.*

interface WeekMvpView : MvpView {

    fun updateWeekNumber(week: Int)

    fun updateDaysFrom(month: Int, firstWeekDay: Calendar)

    fun attachDayClickListener(listener: (DayMvpPresenter) -> Unit)

}