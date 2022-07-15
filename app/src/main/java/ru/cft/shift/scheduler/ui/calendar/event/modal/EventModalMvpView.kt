package ru.cft.shift.scheduler.ui.calendar.event.modal

import ru.cft.shift.scheduler.ui.base.MvpView

interface EventModalMvpView : MvpView {

    val eventId: Long?

    fun showEventMenu()

    fun showShareWindow(url: String)

    fun hideModalWindow()

    fun removeEvent()

}