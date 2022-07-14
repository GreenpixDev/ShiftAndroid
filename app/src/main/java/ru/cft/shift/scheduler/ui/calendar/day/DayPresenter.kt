package ru.cft.shift.scheduler.ui.calendar.day

import ru.cft.shift.scheduler.ui.base.BasePresenter

class DayPresenter : BasePresenter<DayMvpView>(), DayMvpPresenter {

    override var selected = false
    private set

    override fun select() {
        view?.showSelection()
        selected = true
    }

    override fun unselect() {
        view?.hideSelection()
        selected = false
    }
}