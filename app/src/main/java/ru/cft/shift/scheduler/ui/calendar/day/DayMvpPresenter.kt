package ru.cft.shift.scheduler.ui.calendar.day

import ru.cft.shift.scheduler.data.Day
import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.ui.base.MvpPresenter
import java.util.*

interface DayMvpPresenter : MvpPresenter {

    val day: Day

    val selected: Boolean

    fun attachDay(date: Calendar)

    fun select()

    fun unselect()

    fun addEvent(event: Event)

    fun removeEvent(eventId: Long)

}