package ru.cft.shift.scheduler.repository

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import ru.cft.shift.scheduler.di.component.DaggerTestRetrofitComponent
import ru.cft.shift.scheduler.dto.*
import java.net.HttpURLConnection
import javax.inject.Inject
import kotlin.properties.Delegates

class EventRepositoryTest {

    private companion object {

        const val NAME = "test"
        val TYPE = EventType.MEETING
        val COLOR = EventColor.RED
        val UPDATED_COLOR = EventColor.BLUE
        val DATE = DayRequest("2022-07-14T14:27:14.812Z")
        val PERIOD = DateRequest("2022-07-14T14:27:14.812Z", "2022-07-14T14:27:18.812Z")

    }

    @Inject lateinit var authRepository: AuthRepository
    @Inject lateinit var eventRepository: EventRepository

    private var eventId by Delegates.notNull<Long>()

    @Before
    fun before() {
        DaggerTestRetrofitComponent.create().inject(this)
        authRepository.signin(LoginRequest("Greenpix", "my_super_Password_0")).execute()

        val response = eventRepository.create(EventInfoRequest(
            dateRequest = PERIOD,
            name = NAME,
            type = TYPE,
            color = COLOR
        )).execute()
        assertEquals(HttpURLConnection.HTTP_OK, response.code())
        assertNotNull(response.body())
        eventId = response.body()!!.id
    }

    @After
    fun after() {
        val responseDelete = eventRepository.delete(eventId).execute()
        assertEquals(HttpURLConnection.HTTP_OK, responseDelete.code())
    }

    @Test
    fun testUpdate() {
        val responseUpdate = eventRepository.update(eventId, EventInfoRequest(
            dateRequest = PERIOD,
            name = NAME,
            type = TYPE,
            color = UPDATED_COLOR
        )).execute()
        assertEquals(HttpURLConnection.HTTP_OK, responseUpdate.code())
    }

    @Test
    fun testFindEventById() {
        val response = eventRepository.findById(eventId).execute()

        assertEquals(HttpURLConnection.HTTP_OK, response.code())
    }

    @Test
    fun testFindEventByDay() {
        val response = eventRepository.findByDate(DATE).execute()

        assertEquals(HttpURLConnection.HTTP_OK, response.code())
    }

    @Test
    fun testFindEventByPeriod() {
        val response = eventRepository.findByPeriod(PERIOD).execute()

        assertEquals(HttpURLConnection.HTTP_OK, response.code())
    }
}