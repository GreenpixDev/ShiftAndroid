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

private lateinit var binding: ActivityMainBinding
//private lateinit var detector: GestureDetectorCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonInput.setOnClickListener {
            dataChecker()
        }

        binding.buttonInviteToRegistration.setOnClickListener {
            var intent = Intent(MainActivity@this, RegistrationActivity::class.java)
            startActivity(intent)
        }



        //detector = GestureDetectorCompat(this, DiaryGestureListener())

    }

//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return if (detector.onTouchEvent(event)) {
//            true
//        }
//
//        else {
//            return super.onTouchEvent(event)
//        }
//    }

//    inner class DiaryGestureListener: GestureDetector.SimpleOnGestureListener() {
//        private val SWIPE_TRESHOLD = 100
//        private val SWIPE_VELOCITY_TRESHOLD = 100
//
//        override fun onFling(
//            downEvent: MotionEvent?,
//            moveEvent: MotionEvent?,
//            velocityX: Float,
//            velocityY: Float
//        ): Boolean {
//            var diffx = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
//            var diffy = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F
//
//             return if (Math.abs(diffx) > Math.abs(diffy)) {
//                 if (Math.abs(diffx) > SWIPE_TRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_TRESHOLD) {
//                     if (diffx > 0) {
//                         binding.textInputLayoutPassword.OnSwipeRight()
//                     }
//                 }
//             }
//
//
//        }
//    }


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

private fun TextInputLayout.onSwipeRight() {
    binding.password.setText("")
}
