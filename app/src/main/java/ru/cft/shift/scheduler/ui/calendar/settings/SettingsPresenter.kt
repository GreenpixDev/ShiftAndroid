package ru.cft.shift.scheduler.ui.calendar.settings

import ru.cft.shift.scheduler.ui.base.BasePresenter

class SettingsPresenter : BasePresenter<SettingsMvpView>(), SettingsMvpPresenter {

    override fun onLogoutClick() {
        view?.showLoginMenu()
    }

}