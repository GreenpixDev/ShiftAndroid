package ru.cft.shift.scheduler.mvp.ui.calendar.week

import ru.cft.shift.scheduler.mvp.ui.base.MvpPresenter
import ru.cft.shift.scheduler.mvp.ui.calendar.day.DayMvpPresenter

interface WeekMvpPresenter : MvpPresenter {

    val dayPresenters: MutableList<DayMvpPresenter>

}