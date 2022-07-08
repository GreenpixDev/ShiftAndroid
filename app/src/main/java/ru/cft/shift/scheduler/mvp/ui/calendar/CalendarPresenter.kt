package ru.cft.shift.scheduler.mvp.ui.calendar

import ru.cft.shift.scheduler.mvp.ui.base.BasePresenter

class CalendarPresenter : BasePresenter<CalendarMvpView>(), CalendarMvpPresenter {

    override fun onShowSettingsClick() {
        view?.showSettingsFragment()
    }

}