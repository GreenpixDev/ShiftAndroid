package ru.cft.shift.scheduler.repository

import retrofit2.Call
import retrofit2.http.*
import ru.cft.shift.scheduler.dto.*

interface EventRepository {

    @GET("/api/calendar/get/")
    fun findById(
        @Query("id_event") id: Long
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

    @PUT("/api/calendar/change")
    fun update(
        @Query("id_event") id: Long,
        @Body event: EventInfoRequest
    ): Call<Void>

    @DELETE("/api/calendar/delete")
    fun delete(
        @Query("id_event") id: Long
    ): Call<Void>

}