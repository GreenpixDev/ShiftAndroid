package ru.cft.shift.scheduler.ui.login

import ru.cft.shift.scheduler.ui.base.MvpPresenter

interface LoginMvpPresenter : MvpPresenter {

    fun onLoginClick(username: String, password: CharSequence)

    fun onRegisterClick()

}