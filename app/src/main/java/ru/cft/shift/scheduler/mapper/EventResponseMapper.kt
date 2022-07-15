package ru.cft.shift.scheduler.mapper

import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.dto.EventInfoResponse
import ru.cft.shift.scheduler.dto.EventType
import java.util.*
import javax.inject.Inject

class EventResponseMapper @Inject constructor() : Mapper<EventInfoResponse, Event> {

    override fun map(obj: EventInfoResponse): Event {
        return Event(
            id = obj.id,
            name = obj.name,
            begin = obj.dateRequest.startDate,
            end = obj.dateRequest.endDate,
            color = obj.color,
            type = EventType.valueOf(obj.type.uppercase(Locale.ROOT))
        )
    }

}