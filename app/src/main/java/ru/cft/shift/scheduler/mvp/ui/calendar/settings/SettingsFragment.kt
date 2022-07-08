package ru.cft.shift.scheduler.mvp.ui.calendar.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.mvp.ui.base.BaseFragment

class SettingsFragment : BaseFragment<SettingsMvpPresenter>(), SettingsMvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun createPresenter() = SettingsPresenter()

    companion object {

        fun newInstance(param1: String, param2: String) = SettingsFragment()

    }
}