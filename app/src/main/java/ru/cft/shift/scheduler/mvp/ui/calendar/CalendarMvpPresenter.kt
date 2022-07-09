package ru.cft.shift.scheduler.mvp.ui.calendar

import ru.cft.shift.scheduler.mvp.ui.base.MvpPresenter

interface CalendarMvpPresenter : MvpPresenter {

    fun onAttachYearAndMonth(year: Int, month: Int)

    fun onShowSettingsClick()

    fun onNextMonthClick()

    fun onPreviousMonthClick()

}