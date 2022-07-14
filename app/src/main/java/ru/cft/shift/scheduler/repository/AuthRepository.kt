package ru.cft.shift.scheduler.repository

import retrofit2.Call
import retrofit2.http.*
import ru.cft.shift.scheduler.dto.LoginRequest
import ru.cft.shift.scheduler.dto.PasswordRequest
import ru.cft.shift.scheduler.dto.SignupRequest

interface AuthRepository {

    @POST("/api/auth/signup")
    fun signup(
        @Body body: SignupRequest
    ): Call<Void>

    @POST("/api/auth/signout")
    fun signout(): Call<Void>

    @POST("/api/auth/signin")
    fun signin(
        @Body body: LoginRequest
    ): Call<Void>

    @POST("/api/auth/savePassword")
    fun savePassword(
        @Body body: PasswordRequest
    ): Call<Void>

    @POST("/api/auth/savePassword")
    fun resetPassword(
        @Body body: String
    ): Call<Void>

    @GET("/api/auth/signupConfirm/{token}")
    fun signupConfirm(
        @Path("token") token: String
    ): Call<Void>

    @GET("/api/auth/resendRegistrationToken")
    fun resendRegistrationToken(
        @Query("token") token: String
    ): Call<Void>

}