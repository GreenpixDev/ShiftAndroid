package ru.cft.shift.scheduler.ui.event

import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.dto.EventInfoResponse
import ru.cft.shift.scheduler.mapper.EventRequestMapper
import ru.cft.shift.scheduler.mapper.EventResponseMapper
import ru.cft.shift.scheduler.repository.EventRepository
import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.utils.CallbackBuilder
import javax.inject.Inject

class EventPresenter @Inject constructor(
    private val eventRepository: EventRepository,
    private val eventResponseMapper: EventResponseMapper,
    private val eventRequestMapper: EventRequestMapper
) : BasePresenter<EventMvpView>(), EventMvpPresenter {

    override fun onAttachView(mvpView: EventMvpView) {
        super.onAttachView(mvpView)

        view?.let { view ->
            view.eventId?.let { eventId ->
                eventRepository.findById(eventId).enqueueSafe(CallbackBuilder.create<EventInfoResponse>()
                    .onResponse { _, response ->
                        response.body()?.let { view.update(eventResponseMapper.map(it)) }
                    }
                    .build()
                )
            }
        }
    }

    override fun createOrUpdate(event: Event) {
        val request = eventRequestMapper.map(event);

        if (view?.eventId == null) {
            eventRepository.create(request).enqueueSafe(CallbackBuilder.create<EventInfoResponse>()
                .onResponse { _, response ->
                    if (response.isSuccessful) {
                        view?.showCalendarScreen()
                    }
                }
                .build()
            )
        }
        else {
            eventRepository.update(view?.eventId!!, request).enqueueSafe(CallbackBuilder.create<Void>()
                .onResponse { _, response ->
                    if (response.isSuccessful) {
                        view?.showCalendarScreen()
                    }
                }
                .build()
            )
        }
    }

}