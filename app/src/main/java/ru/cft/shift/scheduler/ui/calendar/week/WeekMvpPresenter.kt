package ru.cft.shift.scheduler.ui.calendar.week

import ru.cft.shift.scheduler.ui.base.MvpPresenter
import ru.cft.shift.scheduler.ui.calendar.day.DayMvpPresenter

interface WeekMvpPresenter : MvpPresenter {

    val dayPresenters: MutableList<DayMvpPresenter>

}