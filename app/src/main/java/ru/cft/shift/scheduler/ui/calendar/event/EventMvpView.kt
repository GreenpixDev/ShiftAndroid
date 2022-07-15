package ru.cft.shift.scheduler.ui.calendar.event

import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.ui.base.MvpView

interface EventMvpView : MvpView {

    fun update(event: Event)

    fun attachActionClickListener(listener: (EventMvpPresenter) -> Unit)

    fun remove()

}