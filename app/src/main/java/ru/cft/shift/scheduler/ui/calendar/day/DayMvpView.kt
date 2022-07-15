package ru.cft.shift.scheduler.ui.calendar.day

import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.ui.base.MvpView

interface DayMvpView : MvpView {

    fun attachClickListener(listener: (DayMvpPresenter) -> Unit)

    fun updateDayNumber(day: Int)

    fun updateIsWeekend(value: Boolean)

    fun updateIsCurrentMonth(value: Boolean)

    fun showSelection()

    fun hideSelection()

    fun addEventView(event: Event)

    fun removeEvent(eventId: Long)

}