package ru.cft.shift.scheduler.ui.event

import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.ui.base.MvpPresenter

interface EventMvpPresenter : MvpPresenter {

    fun createOrUpdate(event: Event)

}