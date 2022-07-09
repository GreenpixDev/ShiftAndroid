package ru.cft.shift.scheduler.mvp.ui.calendar.week

import ru.cft.shift.scheduler.mvp.ui.base.MvpView
import java.util.*

interface WeekMvpView : MvpView {

    fun updateWeekNumber(week: Int)

    fun updateDaysFrom(month: Int, firstWeekDay: Calendar)

}