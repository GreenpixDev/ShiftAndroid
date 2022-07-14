package ru.cft.shift.scheduler.repository

import retrofit2.http.*
import ru.cft.shift.scheduler.dto.LoginRequest
import ru.cft.shift.scheduler.dto.PasswordRequest
import ru.cft.shift.scheduler.dto.SignupRequest

interface AuthRepository {

    @POST("/api/auth/signup")
    fun signup(
        @Body body: SignupRequest
    )

    @POST("/api/auth/signout")
    fun signout()

    @POST("/api/auth/signin")
    fun signin(
        @Body body: LoginRequest
    )

    @POST("/api/auth/savePassword")
    fun savePassword(
        @Body body: PasswordRequest
    )

    @POST("/api/auth/savePassword")
    fun resetPassword(
        @Body body: String
    )

    @GET("/api/auth/signupConfirm/{token}")
    fun signupConfirm(
        @Path("token") token: String
    )

    @GET("/api/auth/resendRegistrationToken")
    fun resendRegistrationToken(
        @Query("token") token: String
    )

}