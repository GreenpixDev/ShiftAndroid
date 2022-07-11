package ru.cft.shift.scheduler.mvp.ui.calendar.day

import ru.cft.shift.scheduler.mvp.ui.base.MvpPresenter

interface DayMvpPresenter : MvpPresenter {

    val selected: Boolean

    fun select()

    fun unselect()

}