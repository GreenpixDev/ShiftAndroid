package ru.cft.shift.scheduler.repository

import retrofit2.http.*
import ru.cft.shift.scheduler.dto.*

interface EventRepository {

    @GET("/api/calendar/{id}")
    fun findById(
        @Path("id") id: Long
    ): EventInfoResponse

    @POST("/api/calendar/day")
    fun findByDate(
        @Body day: DayRequest
    ): EventsResponse

    @POST("/api/calendar")
    fun findByPeriod(
        @Body period: DateRequest
    ): EventsResponse

    @POST("/api/calendar/add_event")
    fun create(
        @Body event: EventInfoRequest
    )

    @PUT("/api/calendar/{id}/change")
    fun update(
        @Path("id") id: Long,
        @Body event: EventInfoRequest
    )

    @DELETE("/api/calendar/{id}/delete")
    fun delete(
        @Path("id") id: Long
    )

}