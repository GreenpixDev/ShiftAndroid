package ru.cft.shift.scheduler.ui.calendar.event.modal

import ru.cft.shift.scheduler.repository.EventRepository
import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.utils.CallbackBuilder
import javax.inject.Inject

class EventModalPresenter @Inject constructor(
    private val eventRepository: EventRepository
) : BasePresenter<EventModalMvpView>(), EventModalMvpPresenter {

    override fun onDeleteEvent() {
        eventRepository.delete(1).enqueueSafe(CallbackBuilder.create<Void>()
            .onResponse { _, _ -> view?.hideModalWindow() }
            .onFailure { _, _ -> view?.hideModalWindow() }
            .build()
        )
    }

}