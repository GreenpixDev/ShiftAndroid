package ru.cft.shift.scheduler.ui.calendar.event

import ru.cft.shift.scheduler.ui.base.BasePresenter

class EventPresenter : BasePresenter<EventMvpView>(), EventMvpPresenter {

    override var eventId: Long? = null

    override fun remove() {
        view?.remove()
    }

}