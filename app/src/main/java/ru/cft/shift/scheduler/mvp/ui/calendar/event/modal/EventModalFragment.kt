package ru.cft.shift.scheduler.mvp.ui.calendar.event.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.mvp.ui.base.BaseFragment

class EventModalFragment : BaseFragment<EventModalMvpPresenter>(), EventModalMvpView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun createPresenter() = EventModalPresenter()
}