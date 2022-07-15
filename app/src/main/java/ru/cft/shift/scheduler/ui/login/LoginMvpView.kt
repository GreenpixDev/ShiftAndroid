package ru.cft.shift.scheduler.ui.login

import ru.cft.shift.scheduler.ui.base.MvpView

interface LoginMvpView : MvpView {

    fun showBadCredentials()

    fun showConnectionError()

    fun showRegistrationScreen()

    fun showCalendarScreen()

}