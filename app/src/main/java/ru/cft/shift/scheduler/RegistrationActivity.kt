package ru.cft.shift.scheduler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.cft.shift.scheduler.databinding.ActivityMainBinding
import ru.cft.shift.scheduler.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            var intent = Intent(RegistrationActivity@this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}