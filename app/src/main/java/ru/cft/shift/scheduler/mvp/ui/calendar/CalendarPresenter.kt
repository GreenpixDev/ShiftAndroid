package ru.cft.shift.scheduler.mvp.ui.calendar

import ru.cft.shift.scheduler.mvp.ui.base.BasePresenter

class CalendarPresenter : BasePresenter<CalendarMvpView>(), CalendarMvpPresenter {

    private var year: Int = 0
    private var month: Int = 0

    override fun onAttachYearAndMonth(year: Int, month: Int) {
        this.year = year
        this.month = month
    }

    override fun onShowSettingsClick() {
        view?.showSettingsFragment()
    }

    override fun onNextMonthClick() {
        view?.showCalendar(year + Math.floorDiv(month + 1, 12), Math.floorMod(month + 1, 12))
    }

    override fun onPreviousMonthClick() {
        view?.showCalendar(year + Math.floorDiv(month - 1, 12), Math.floorMod(month - 1, 12))
    }

}