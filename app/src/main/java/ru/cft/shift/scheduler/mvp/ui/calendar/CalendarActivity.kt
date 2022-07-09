package ru.cft.shift.scheduler.mvp.ui.calendar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.core.view.get
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.databinding.ActivityCalendarBinding
import ru.cft.shift.scheduler.mvp.ui.base.BaseActivity
import ru.cft.shift.scheduler.mvp.ui.calendar.week.WeekMvpView
import java.text.DateFormatSymbols
import java.util.*

class CalendarActivity : BaseActivity<CalendarMvpPresenter>(), CalendarMvpView {

    private lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settings.setOnClickListener { presenter.onShowSettingsClick() }
        binding.nextMonth.setOnClickListener { presenter.onNextMonthClick() }
        binding.previousMonth.setOnClickListener { presenter.onPreviousMonthClick() }

        initCalendar()
    }

    @SuppressLint("SetTextI18n")
    private fun initCalendar() {
        val date = Calendar.getInstance()
        val year = intent.getIntExtra(MESSAGE_YEAR, date.get(Calendar.YEAR))
        val month = intent.getIntExtra(MESSAGE_MONTH, date.get(Calendar.MONTH))

        date.set(year, month, 1)
        val dayOfWeek = Math.floorMod(date.get(Calendar.DAY_OF_WEEK) - 2, 7)

        date.add(Calendar.DAY_OF_YEAR, -dayOfWeek)

        for (row in 1..6) {
            if (row > 1 && date.get(Calendar.MONTH) != month) {
                binding.calendarLayout.removeViewAt(row)
            }
            else {
                val weekView = binding.calendarLayout[row] as WeekMvpView
                val weekNumber = date.get(Calendar.WEEK_OF_YEAR)

                weekView.updateWeekNumber(weekNumber)
                weekView.updateDaysFrom(month, date)
            }
            date.add(Calendar.DAY_OF_YEAR, 7)
        }
        binding.month.text = "$year ${DateFormatSymbols().months[month]}"

        presenter.onAttachYearAndMonth(year, month)
    }

    override fun createPresenter() = CalendarPresenter()

    override fun showCalendar(year: Int, month: Int) {
        val intent = Intent(this, CalendarActivity::class.java).apply {
            putExtra(MESSAGE_YEAR, year)
            putExtra(MESSAGE_MONTH, month)
        }
        startActivity(intent)
    }

    override fun showSettingsFragment() {
        // TODO убрать заглушку и сделать
        println("Show Settings")
    }

    private companion object {

        const val MESSAGE_YEAR = "year"
        const val MESSAGE_MONTH = "month"

    }
}