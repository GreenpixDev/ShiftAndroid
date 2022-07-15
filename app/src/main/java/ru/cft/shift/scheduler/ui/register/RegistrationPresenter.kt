package ru.cft.shift.scheduler.ui.register

import ru.cft.shift.scheduler.dto.LoginRequest
import ru.cft.shift.scheduler.dto.SignupRequest
import ru.cft.shift.scheduler.repository.AuthRepository
import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.utils.CallbackBuilder
import javax.inject.Inject

class RegistrationPresenter @Inject constructor(
    val authRepository: AuthRepository
) : BasePresenter<RegistrationMvpView>(), RegistrationMvpPresenter {

    private companion object {
        val PASSWORD_REGEX = Regex("""^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*${'$'}""")
        val LOGIN_REGEX = Regex("""^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*${'$'}""")
        val EMAIL_REGEX = Regex(""".*""") // TODO сделать норм регулярку для почты
    }

    override fun onLoginClick() {
        view?.showLoginScreen()
    }

    override fun onRegisterClick(
        username: String,
        email: String,
        password: CharSequence,
        repeatPassword: CharSequence
    ) {
        if (!password.matches(PASSWORD_REGEX)) {
            view?.showInvalidPasswordToast()
            return
        }

        if (password != repeatPassword) {
            view?.showPasswordNotMatchToast()
            return
        }

        if (!username.matches(EMAIL_REGEX)) {
            view?.showInvalidEmailToast()
            return
        }

        if (!username.matches(LOGIN_REGEX)) {
            view?.showInvalidUsernameToast()
            return
        }

        authRepository.signup(SignupRequest(
            login = username,
            email = email,
            password = password.toString()
        )).enqueueSafe(
            CallbackBuilder.create<Void>()
            .onResponse { _, response ->
                if (response.isSuccessful) {
                    view?.showLoginScreen()
                }
                else {
                    view?.showUserExists()
                }
            }
            .onFailure { _, _ -> view?.showConnectionError() }
            .build()
        )
    }
}