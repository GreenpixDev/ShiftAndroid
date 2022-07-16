package ru.cft.shift.scheduler.ui.calendar

import ru.cft.shift.scheduler.data.Day
import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.data.Month
import ru.cft.shift.scheduler.ui.base.MvpPresenter
import ru.cft.shift.scheduler.ui.calendar.day.DayMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.event.EventMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.week.WeekMvpPresenter

interface CalendarMvpPresenter : MvpPresenter {

    val month: Month

    val selectedDay: Day?

    val weekPresenters: MutableList<WeekMvpPresenter>

    val eventPresenters: MutableList<EventMvpPresenter>

    val dayPresenters: MutableMap<Day, DayMvpPresenter>

    fun attachMonth(year: Int, month: Int)

    fun attachDay(presenter: DayMvpPresenter)

    fun loadEvents()

    fun selectDay(presenter: DayMvpPresenter)

    fun unselectDay()

    fun removeEvent(event: Event)

    fun onShowSettingsClick()

    fun onNextMonthClick()

    fun onPreviousMonthClick()

    fun onAddEventClick()

    fun onDayClick(presenter: DayMvpPresenter)

}