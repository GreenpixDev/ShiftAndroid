package ru.cft.shift.scheduler.ui.login

import ru.cft.shift.scheduler.dto.LoginRequest
import ru.cft.shift.scheduler.repository.AuthRepository
import ru.cft.shift.scheduler.service.SessionCookieJar
import ru.cft.shift.scheduler.ui.base.BaseActivity
import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.utils.CallbackBuilder
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val authRepository: AuthRepository,
    private val cookieJar: SessionCookieJar
) : BasePresenter<LoginMvpView>(), LoginMvpPresenter {

    override fun onLoginClick(username: String, password: CharSequence) {
        authRepository.signin(LoginRequest(
            username = username,
            password = password.toString()
        )).enqueueSafe(CallbackBuilder.create<Void>()
            .onResponse { _, response ->
                if (response.isSuccessful) {
                    view?.showCalendarScreen(cookieJar[BaseActivity.COOKIE_JWT_TOKEN_NAME]?.value())
                }
                else {
                    view?.showBadCredentials()
                }
            }
            .onFailure { _, _ -> view?.showConnectionError() }
            .build()
        )
    }

    override fun onRegisterClick() {
        view?.showRegistrationScreen()
    }
}