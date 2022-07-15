package ru.cft.shift.scheduler.ui.register

import ru.cft.shift.scheduler.ui.base.MvpPresenter

interface RegistrationMvpPresenter : MvpPresenter {

    fun onLoginClick()

    fun onRegisterClick(
        username: String,
        email: String,
        password: CharSequence,
        repeatPassword: CharSequence
    )

}