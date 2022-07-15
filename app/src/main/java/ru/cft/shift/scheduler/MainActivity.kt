package ru.cft.shift.scheduler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.Gravity
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import com.google.android.material.textfield.TextInputLayout
import ru.cft.shift.scheduler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private companion object {
        val PASSWORD_REGEX = Regex("""^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*${'$'}""")
        val LOGIN_REGEX = Regex("""^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*${'$'}""")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonInput.setOnClickListener {
            dataChecker()
        }

        binding.buttonInviteToRegistration.setOnClickListener {
            intentToRegisrationActivity()
        }
    }

    private fun dataChecker() {
        val passwordString = binding.password.text.toString()
        val loginString = binding.login.text.toString()

        if (!passwordString.matches(PASSWORD_REGEX)) {
            val passwordErrorToast = Toast.makeText(
                this,
                R.string.PASSWORD_ERROR_MESSAGE,
                Toast.LENGTH_LONG
            )
            passwordErrorToast.setGravity(Gravity.TOP, 0, 0)
            passwordErrorToast.show()

            return
        }

        if (!loginString.matches(LOGIN_REGEX)) {
            val loginErrorToast = Toast.makeText(
                this,
                R.string.LOGIN_ERROR_MESSAGE,
                Toast.LENGTH_LONG
            )

            loginErrorToast.setGravity(Gravity.TOP, 0, 0)
            loginErrorToast.show()
        }
    }

    private fun intentToRegisrationActivity() {
        val intent = Intent(MainActivity@this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}

