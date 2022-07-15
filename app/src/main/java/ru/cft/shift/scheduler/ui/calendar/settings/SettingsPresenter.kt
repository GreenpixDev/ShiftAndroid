package ru.cft.shift.scheduler.ui.calendar.settings

import ru.cft.shift.scheduler.repository.AuthRepository
import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.utils.CallbackBuilder
import javax.inject.Inject

class SettingsPresenter @Inject constructor(
    private val authRepository: AuthRepository
) : BasePresenter<SettingsMvpView>(), SettingsMvpPresenter {

    override fun onLogoutClick() {
        authRepository.signout().enqueueSafe(CallbackBuilder.create<Void>()
            .onResponse { _, _ -> view?.showLoginMenu() }
            .build()
        )
    }

}