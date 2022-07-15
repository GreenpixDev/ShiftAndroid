package ru.cft.shift.scheduler.ui.calendar.day

import ru.cft.shift.scheduler.data.Day
import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.ui.base.BasePresenter
import java.util.*

class DayPresenter : BasePresenter<DayMvpView>(), DayMvpPresenter {

    override lateinit var day: Day
    private set

    override var selected = false
    private set

    override fun attachDay(date: Calendar) {
        day = Day(
            yearNumber = date.get(Calendar.YEAR),
            monthNumber = date.get(Calendar.MONTH),
            dayNumber = date.get(Calendar.DAY_OF_MONTH)
        )
    }

    override fun select() {
        view?.showSelection()
        selected = true
    }

    override fun unselect() {
        view?.hideSelection()
        selected = false
    }

    override fun addEvent(event: Event) {
        view?.addEventView(event)
    }

    override fun removeEvent(eventId: Long) {
        view?.removeEvent(eventId)
    }
}