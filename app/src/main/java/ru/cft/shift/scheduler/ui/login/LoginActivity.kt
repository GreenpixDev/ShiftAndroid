package ru.cft.shift.scheduler.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.databinding.ActivityLoginBinding
import ru.cft.shift.scheduler.ui.register.RegistrationActivity
import ru.cft.shift.scheduler.ui.base.BaseActivity
import ru.cft.shift.scheduler.ui.calendar.CalendarActivity
import javax.inject.Inject

class LoginActivity : BaseActivity<LoginMvpPresenter>(), LoginMvpView {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    override lateinit var presenter: LoginMvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonInput.setOnClickListener { presenter.onLoginClick(
            password = binding.password.text.toString(),
            username = binding.login.text.toString()
        ) }
        binding.buttonInviteToRegistration.setOnClickListener { presenter.onRegisterClick() }

        component.inject(this)
    }

    override fun showRegistrationScreen() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }

    override fun showCalendarScreen() {
        val intent = Intent(this, CalendarActivity::class.java)
        startActivity(intent)
    }

    override fun showBadCredentials() {
        Toast.makeText(
            this,
            R.string.bad_credentials,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showConnectionError() {
        Toast.makeText(
            this,
            R.string.connection_error,
            Toast.LENGTH_SHORT
        ).show()
    }
}

