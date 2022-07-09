package ru.cft.shift.scheduler.mvp.ui.calendar.day

import ru.cft.shift.scheduler.mvp.ui.base.MvpView

interface DayMvpView : MvpView {

    fun updateDayNumber(day: Int)

    fun updateIsWeekend(value: Boolean)

    fun updateIsCurrentMonth(value: Boolean)

}