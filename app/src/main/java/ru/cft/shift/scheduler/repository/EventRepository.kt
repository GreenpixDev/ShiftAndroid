package ru.cft.shift.scheduler.repository

import retrofit2.Call
import retrofit2.http.*
import ru.cft.shift.scheduler.dto.*

interface EventRepository {

    @GET("/api/calendar/{id}")
    fun findById(
        @Path("id") id: Long
    ): Call<EventInfoResponse>

    @POST("/api/calendar/day")
    fun findByDate(
        @Body day: DayRequest
    ): Call<EventsResponse>

    @POST("/api/calendar")
    fun findByPeriod(
        @Body period: DateRequest
    ): Call<EventsResponse>

    @POST("/api/calendar/add_event")
    fun create(
        @Body event: EventInfoRequest
    ): Call<EventInfoResponse>

    @PUT("/api/calendar/{id}/change")
    fun update(
        @Path("id") id: Long,
        @Body event: EventInfoRequest
    ): Call<Void>

    @DELETE("/api/calendar/{id}/delete")
    fun delete(
        @Path("id") id: Long
    ): Call<Void>

}