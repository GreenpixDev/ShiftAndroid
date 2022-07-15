package ru.cft.shift.scheduler.ui.calendar

import android.util.Log
import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.data.Month
import ru.cft.shift.scheduler.dto.DateRequest
import ru.cft.shift.scheduler.dto.DayRequest
import ru.cft.shift.scheduler.dto.EventsResponse
import ru.cft.shift.scheduler.repository.EventRepository
import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.ui.calendar.day.DayMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.event.EventMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.week.WeekMvpPresenter
import ru.cft.shift.scheduler.utils.CallbackBuilder
import javax.inject.Inject

class CalendarPresenter @Inject constructor(
    private val eventRepository: EventRepository
) : BasePresenter<CalendarMvpView>(), CalendarMvpPresenter {

    override lateinit var month: Month
    private set

    override val weekPresenters = mutableListOf<WeekMvpPresenter>()
    override val eventPresenters = mutableListOf<EventMvpPresenter>()

    override fun attachMonth(year: Int, month: Int) {
        this.month = Month(year, month)
    }

    override fun loadEvents() {
        eventRepository.findByPeriod(DateRequest(
            startDate = month.calendar.time,
            endDate = month.next().calendar.time
        )).enqueueSafe(CallbackBuilder.create<EventsResponse>()
            .onResponse { _, response ->
                response.body()?.let { body ->
                    body.items.forEach {
                        Log.d("Info", "${it.id}") // TODO добавить отображение в календаре
                    }
                }
            }
            .build()
        )
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

        eventRepository.findByDate(DayRequest(
            presenter.day.calendar.time,
        )).enqueueSafe(CallbackBuilder.create<EventsResponse>()
            .onResponse { _, response ->
                view?.clearEventViews()

                response.body()?.let { body ->
                    body.items.forEach {
                        view?.addEventView(Event(
                            id = it.id,
                            name = it.name,
                            begin = (it.dateRequest.startDate),
                            end = it.dateRequest.endDate,
                            color = it.color,
                            type = it.type
                        ))
                    }
                }
            }
            .build()
        )
    }

    override fun onShowSettingsClick() {
        view?.showSettingsModalWindow()
    }

    override fun onNextMonthClick() {
        val month = this.month.next()
        view?.showCalendar(month.yearNumber, month.monthNumber)
    }

    override fun onPreviousMonthClick() {
        val month = this.month.previous()
        view?.showCalendar(month.yearNumber, month.monthNumber)
    }

    override fun onDayClick(presenter: DayMvpPresenter) {
        selectDay(presenter)
    }
}