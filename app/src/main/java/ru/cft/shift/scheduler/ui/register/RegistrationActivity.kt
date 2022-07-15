package ru.cft.shift.scheduler.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.databinding.ActivityRegistrationBinding
import ru.cft.shift.scheduler.ui.base.BaseActivity
import ru.cft.shift.scheduler.ui.login.LoginActivity
import javax.inject.Inject

class RegistrationActivity : BaseActivity<RegistrationMvpPresenter>(), RegistrationMvpView {

    private lateinit var binding: ActivityRegistrationBinding

    @Inject
    override lateinit var presenter: RegistrationMvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener { presenter.onLoginClick() }
        binding.buttonInviteToRegistration.setOnClickListener { presenter.onRegisterClick(
            username = binding.login.text.toString(),
            email = binding.email.text.toString(),
            password = binding.password.text.toString(),
            repeatPassword = binding.passwordRepeat.text.toString()
        ) }

        component.inject(this)
    }

    override fun showInvalidUsernameToast() {
        Toast.makeText(
            this,
            R.string.invalid_username,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showInvalidPasswordToast() {
        Toast.makeText(
            this,
            R.string.invalid_password,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showInvalidEmailToast() {
        Toast.makeText(
            this,
            R.string.invalid_email,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showPasswordNotMatchToast() {
        Toast.makeText(
            this,
            R.string.password_not_match,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showUserExists() {
        Toast.makeText(
            this,
            R.string.user_exists,
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

    override fun showLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        Toast.makeText(
            this,
            R.string.email_sent,
            Toast.LENGTH_SHORT
        ).show()
    }
}