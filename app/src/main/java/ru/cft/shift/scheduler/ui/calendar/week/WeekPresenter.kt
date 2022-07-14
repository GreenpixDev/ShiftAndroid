package ru.cft.shift.scheduler.ui.calendar.week

import ru.cft.shift.scheduler.ui.base.BasePresenter
import ru.cft.shift.scheduler.ui.calendar.day.DayMvpPresenter

class WeekPresenter : BasePresenter<WeekMvpView>(), WeekMvpPresenter {

    override val dayPresenters = mutableListOf<DayMvpPresenter>()

}