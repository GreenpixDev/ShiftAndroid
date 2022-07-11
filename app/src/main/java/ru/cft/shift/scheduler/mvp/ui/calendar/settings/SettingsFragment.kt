package ru.cft.shift.scheduler.mvp.ui.calendar.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.databinding.FragmentSettingsBinding
import ru.cft.shift.scheduler.mvp.ui.base.BaseFragment

class SettingsFragment : BaseFragment<SettingsMvpPresenter>(), SettingsMvpView {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        binding.background.setOnClickListener { hideModalWindow() }
        binding.window.setOnClickListener(null)

        return binding.root
    }

    private fun hideModalWindow() {
        val fragment = parentFragmentManager.findFragmentById(R.id.modal_window)
        fragment?.let {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.remove(it)
            transaction.commit()
        }
    }

    override fun createPresenter() = SettingsPresenter()
}