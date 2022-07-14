package ru.cft.shift.scheduler.ui.calendar.event

import ru.cft.shift.scheduler.data.Mark
import ru.cft.shift.scheduler.ui.base.MvpView

interface EventMvpView : MvpView {

    fun updateMark(mark: Mark)

    fun attachActionClickListener(listener: (EventMvpPresenter) -> Unit)

}