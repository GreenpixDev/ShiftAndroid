package ru.cft.shift.scheduler.ui.calendar

import ru.cft.shift.scheduler.data.Day
import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.data.Month
import ru.cft.shift.scheduler.dto.DateRequest
import ru.cft.shift.scheduler.dto.DayRequest
import ru.cft.shift.scheduler.dto.EventsResponse
import ru.cft.shift.scheduler.mapper.EventResponseMapper
import ru.cft.shift.scheduler.repository.EventRepository
import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.ui.calendar.day.DayMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.event.EventMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.week.WeekMvpPresenter
import ru.cft.shift.scheduler.utils.CallbackBuilder
import java.util.*
import javax.inject.Inject

class CalendarPresenter @Inject constructor(
    private val eventRepository: EventRepository,
    private val eventMapper: EventResponseMapper
) : BasePresenter<CalendarMvpView>(), CalendarMvpPresenter {

    override lateinit var month: Month
    private set

    override var selectedDay: Day? = null
    private set

    override val weekPresenters = mutableListOf<WeekMvpPresenter>()
    override val eventPresenters = mutableListOf<EventMvpPresenter>()
    override val dayPresenters = hashMapOf<Day, DayMvpPresenter>()

    override fun attachMonth(year: Int, month: Int) {
        this.month = Month(year, month)
    }

    override fun attachDay(presenter: DayMvpPresenter) {
        dayPresenters[presenter.day] = presenter
    }

    override fun loadEvents() {
        eventRepository.findByPeriod(DateRequest(
            startDate = month.calendar.time,
            endDate = month.next().calendar.time
        )).enqueueSafe(CallbackBuilder.create<EventsResponse>()
            .onResponse { _, response ->
                response.body()?.let { body ->
                    body.items.forEach { event ->
                        val begin = Calendar.getInstance()
                        val end = Calendar.getInstance()

                        begin.time = event.dateRequest.startDate
                        end.time = event.dateRequest.endDate

                        while (begin.get(Calendar.DAY_OF_YEAR) <= end.get(Calendar.DAY_OF_YEAR)) {
                            val day = Day(
                                yearNumber = begin.get(Calendar.YEAR),
                                monthNumber = begin.get(Calendar.MONTH),
                                dayNumber = begin.get(Calendar.DAY_OF_MONTH)
                            )
                            dayPresenters[day]?.addEvent(eventMapper.map(event))
                            begin.add(Calendar.DAY_OF_YEAR, 1)
                        }
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
        selectedDay = null
    }

    override fun selectDay(presenter: DayMvpPresenter) {
        if (presenter.selected) return
        unselectDay()
        presenter.select()
        selectedDay = presenter.day

        eventRepository.findByDate(DayRequest(
            presenter.day.calendar.time,
        )).enqueueSafe(CallbackBuilder.create<EventsResponse>()
            .onResponse { _, response ->
                view?.clearEventViews()

                response.body()?.let { body ->
                    body.items.forEach {
                        view?.addEventView(eventMapper.map(it))
                    }
                }
            }
            .build()
        )
    }

    override fun removeEvent(event: Event) {
        val begin = Calendar.getInstance()
        val end = Calendar.getInstance()

        begin.time = event.begin
        end.time = event.end

        while (begin.get(Calendar.DAY_OF_YEAR) <= end.get(Calendar.DAY_OF_YEAR)) {
            val day = Day(
                yearNumber = begin.get(Calendar.YEAR),
                monthNumber = begin.get(Calendar.MONTH),
                dayNumber = begin.get(Calendar.DAY_OF_MONTH)
            )
            dayPresenters[day]?.removeEvent(event.id)
            begin.add(Calendar.DAY_OF_YEAR, 1)
        }
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

    override fun onAddEventClick() {
        view?.showEventMenu()
    }
}