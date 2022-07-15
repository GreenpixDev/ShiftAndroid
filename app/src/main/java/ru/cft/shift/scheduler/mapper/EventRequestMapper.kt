package ru.cft.shift.scheduler.mapper

import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.dto.DateRequest
import ru.cft.shift.scheduler.dto.EventInfoRequest
import java.util.*
import javax.inject.Inject

class EventRequestMapper @Inject constructor() : Mapper<Event, EventInfoRequest> {

    override fun map(obj: Event): EventInfoRequest {
        return EventInfoRequest(
            name = obj.name,
            dateRequest = DateRequest(obj.begin, obj.end),
            color = obj.color,
            type = obj.type.name.lowercase(Locale.ROOT)
        )
    }

}