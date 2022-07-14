package ru.cft.shift.scheduler.ui.calendar

import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.data.Mark
import ru.cft.shift.scheduler.data.Month
import ru.cft.shift.scheduler.repository.EventRepository
import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.ui.calendar.day.DayMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.event.EventMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.week.WeekMvpPresenter
import java.util.*
import javax.inject.Inject

class CalendarPresenter @Inject constructor(
    private val eventRepository: EventRepository
) : BasePresenter<CalendarMvpView>(), CalendarMvpPresenter {

    private lateinit var month: Month

    override val weekPresenters = mutableListOf<WeekMvpPresenter>()
    override val eventPresenters = mutableListOf<EventMvpPresenter>()

    override fun attachYearAndMonth(year: Int, month: Int) {
        this.month = Month(year, month)
    }

    override fun unselectDay() {
        weekPresenters
            .flatMap { it.dayPresenters }
            .forEach { it.unselect() }
    }

    override fun selectDay(presenter: DayMvpPresenter) {
        if (presenter.selected) return
        unselectDay()
        presenter.select()

        view?.clearEventViews()

        // TODO change to for cycle
        view?.addEventView(
            Event(
                Date(),
                Date(),
                Mark(R.color.mark_light_common, R.color.mark_dark_common)
            )
        )
        view?.addEventView(
            Event(
                Date(),
                Date(),
                Mark(R.color.mark_light_red, R.color.mark_dark_red)
            )
        )
        view?.addEventView(
            Event(
                Date(),
                Date(),
                Mark(R.color.mark_light_violet, R.color.mark_dark_violet)
            )
        )
    }

    override fun onShowSettingsClick() {
        view?.showSettingsModalWindow()
    }

    override fun onNextMonthClick() {
        val month = this.month.next()
        view?.showCalendar(month.year, month.month)
    }

    override fun onPreviousMonthClick() {
        val month = this.month.previous()
        view?.showCalendar(month.year, month.month)
    }

    override fun onDayClick(presenter: DayMvpPresenter) {
        selectDay(presenter)
    }
}