package ru.cft.shift.scheduler.ui.calendar.event.modal

import ru.cft.shift.scheduler.ui.base.MvpPresenter

interface EventModalMvpPresenter : MvpPresenter {

    val eventId: Long?

    fun onShareClick()

    fun onDeleteEventClick()

    fun onEditEventClick()

}