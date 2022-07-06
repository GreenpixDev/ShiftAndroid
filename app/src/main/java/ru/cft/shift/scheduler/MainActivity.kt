package ru.cft.shift.scheduler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import ru.cft.shift.scheduler.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonInput.setOnClickListener {
            dataChecker()
        }

    }

    private fun dataChecker() {
        """^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*${'$'}""".toRegex() //регулярка для пароля
        """^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}${'$'}""".toRegex() //регулярка для логина

        var passwordString = binding.password.text.toString()
        var loginString = binding.login.text.toString()

        if ((!passwordString.matches(Regex("""^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*${'$'}""")))) {
            val passwordErrorToast = Toast.makeText(
                this,
                "Пароль должен содержать строчные и прописные латинские буквы, цифры",
                Toast.LENGTH_LONG
            )
            passwordErrorToast.setGravity(Gravity.TOP, 0, 0)
            passwordErrorToast.show()
        }

        if ((!loginString.matches(Regex("""^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}${'$'}""")))) {
            val loginErrorToast = Toast.makeText(
                this,
                "Логин должен состоять из букв и цифр, первый символ обязательно буква, ограничение 20 символов",
                Toast.LENGTH_LONG
            )

            loginErrorToast.setGravity(Gravity.TOP, 0, 0)
            loginErrorToast.show()
        }

    }
    
}