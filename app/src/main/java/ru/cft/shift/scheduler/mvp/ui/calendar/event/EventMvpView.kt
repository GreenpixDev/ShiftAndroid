package ru.cft.shift.scheduler.mvp.ui.calendar.event

import ru.cft.shift.scheduler.mvp.data.Mark
import ru.cft.shift.scheduler.mvp.ui.base.MvpView

interface EventMvpView : MvpView {

    fun updateMark(mark: Mark)

}