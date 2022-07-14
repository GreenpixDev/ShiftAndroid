package ru.cft.shift.scheduler.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import ru.cft.shift.scheduler.di.component.DaggerTestRetrofitComponent
import ru.cft.shift.scheduler.dto.LoginRequest
import ru.cft.shift.scheduler.dto.SignupRequest
import java.net.HttpURLConnection
import javax.inject.Inject

class AuthRepositoryTest {

    @Inject lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        DaggerTestRetrofitComponent.create().inject(this)
    }

    @Test
    fun testSignupInvalidPassword() {
        val response = authRepository.signup(SignupRequest("123", "user", "user@mail.ru"))
            .execute()
        assertNotEquals(HttpURLConnection.HTTP_OK, response.code())
    }

    @Test
    fun testSignin() {
        val response = authRepository.signin(LoginRequest("Greenpix", "my_super_Password_0"))
            .execute()
        assertEquals(HttpURLConnection.HTTP_OK, response.code())
    }
}