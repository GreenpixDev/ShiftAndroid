package ru.cft.shift.scheduler.ui.event

import ru.cft.shift.scheduler.data.Day
import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.ui.base.MvpView

interface EventMvpView : MvpView {

    val day: Day?

    val eventId: Long?

    fun update(event: Event)

    fun showCalendarScreen()

}