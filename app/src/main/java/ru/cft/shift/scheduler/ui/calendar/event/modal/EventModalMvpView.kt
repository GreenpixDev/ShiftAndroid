package ru.cft.shift.scheduler.ui.calendar.event.modal

import ru.cft.shift.scheduler.ui.base.MvpView

interface EventModalMvpView : MvpView {

    fun showEventMenu()

    fun showShareWindow()

    fun hideModalWindow()

}