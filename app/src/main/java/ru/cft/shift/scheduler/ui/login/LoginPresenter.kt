package ru.cft.shift.scheduler.ui.login

import ru.cft.shift.scheduler.dto.LoginRequest
import ru.cft.shift.scheduler.repository.AuthRepository
import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.utils.CallbackBuilder
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    val authRepository: AuthRepository
) : BasePresenter<LoginMvpView>(), LoginMvpPresenter {

    override fun onLoginClick(username: String, password: CharSequence) {
        authRepository.signin(LoginRequest(
            username = username,
            password = password.toString()
        )).enqueueSafe(CallbackBuilder.create<Void>()
            .onResponse { _, response ->
                if (response.isSuccessful) {
                    view?.showCalendarScreen()
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