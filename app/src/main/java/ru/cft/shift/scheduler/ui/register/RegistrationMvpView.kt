package ru.cft.shift.scheduler.ui.register

import ru.cft.shift.scheduler.ui.base.MvpView

interface RegistrationMvpView : MvpView {

    fun showInvalidUsernameToast()

    fun showInvalidPasswordToast()

    fun showInvalidEmailToast()

    fun showPasswordNotMatchToast();

    fun showUserExists()

    fun showConnectionError()

    fun showLoginScreen()

    fun showInvalidPasswordLenghtToast()
}