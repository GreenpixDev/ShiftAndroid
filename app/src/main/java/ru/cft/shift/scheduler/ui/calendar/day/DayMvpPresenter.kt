package ru.cft.shift.scheduler.ui.calendar.day

import ru.cft.shift.scheduler.ui.base.MvpPresenter

interface DayMvpPresenter : MvpPresenter {

    val selected: Boolean

    fun select()

    fun unselect()

}