package ru.cft.shift.scheduler.ui.calendar.event.modal

import ru.cft.shift.scheduler.repository.EventRepository
import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.utils.CallbackBuilder
import javax.inject.Inject

class EventModalPresenter @Inject constructor(
    private val eventRepository: EventRepository
) : BasePresenter<EventModalMvpView>(), EventModalMvpPresenter {

    override val eventId: Long?
        get() = view?.eventId

    override fun onShareClick() {
        eventId?.let {
            view?.showShareWindow("http://plannerrestapi.herokuapp.com/api/calendar/get/?id_event=$it")
        }
    }

    override fun onDeleteEventClick() {
        eventId?.let {
            eventRepository.delete(it).enqueueSafe(CallbackBuilder.create<Void>()
                .onResponse { _, _ ->
                    view?.hideModalWindow()
                    view?.removeEvent()
                }
                .onFailure { _, _ -> view?.hideModalWindow() }
                .build()
            )
        }
    }

    override fun onEditEventClick() {
        eventId?.let {
            view?.showEventMenu()
        }
    }

}