package ru.cft.shift.scheduler.mvp.ui.calendar

import ru.cft.shift.scheduler.mvp.ui.base.MvpPresenter
import ru.cft.shift.scheduler.mvp.ui.calendar.day.DayMvpPresenter
import ru.cft.shift.scheduler.mvp.ui.calendar.event.EventMvpPresenter
import ru.cft.shift.scheduler.mvp.ui.calendar.week.WeekMvpPresenter

interface CalendarMvpPresenter : MvpPresenter {

    val weekPresenters: MutableList<WeekMvpPresenter>

    val eventPresenters: MutableList<EventMvpPresenter>

    fun attachYearAndMonth(year: Int, month: Int)

    fun selectDay(presenter: DayMvpPresenter)

    fun unselectDay()

    fun onShowSettingsClick()

    fun onNextMonthClick()

    fun onPreviousMonthClick()

    fun onDayClick(presenter: DayMvpPresenter)

}