package ru.cft.shift.scheduler.json

import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class JsonDateConverter : JsonSerializer<Date>, JsonDeserializer<Date> {

    private companion object {
        val FORMAT = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.sss")
    }

    override fun serialize(
        src: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src?.let { FORMAT.format(it) })
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date? {
        return json?.asString?.let { FORMAT.parse(it) }
    }
}