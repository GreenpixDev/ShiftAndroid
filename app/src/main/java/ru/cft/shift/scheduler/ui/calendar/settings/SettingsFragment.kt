package ru.cft.shift.scheduler.ui.calendar.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.cft.shift.scheduler.ui.login.LoginActivity
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.databinding.FragmentSettingsBinding
import ru.cft.shift.scheduler.ui.base.BaseFragment
import javax.inject.Inject

class SettingsFragment : BaseFragment<SettingsMvpPresenter>(), SettingsMvpView {

    private lateinit var binding: FragmentSettingsBinding

    @Inject
    override lateinit var presenter: SettingsMvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        binding.background.setOnClickListener { hideModalWindow() }
        binding.window.setOnClickListener(null)
        binding.logout.setOnClickListener { presenter.onLogoutClick() }

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

    override fun showLoginMenu() {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }
}