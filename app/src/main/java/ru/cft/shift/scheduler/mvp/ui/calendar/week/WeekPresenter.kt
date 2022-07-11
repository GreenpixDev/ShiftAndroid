package ru.cft.shift.scheduler.mvp.ui.calendar.week

import ru.cft.shift.scheduler.mvp.ui.base.BasePresenter
import ru.cft.shift.scheduler.mvp.ui.calendar.day.DayMvpPresenter

class WeekPresenter : BasePresenter<WeekMvpView>(), WeekMvpPresenter {

    override val dayPresenters = mutableListOf<DayMvpPresenter>()

}