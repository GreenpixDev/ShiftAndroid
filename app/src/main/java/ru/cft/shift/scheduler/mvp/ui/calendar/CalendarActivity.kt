package ru.cft.shift.scheduler.mvp.ui.calendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.get
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.databinding.ActivityCalendarBinding
import ru.cft.shift.scheduler.mvp.ui.base.BaseActivity
import ru.cft.shift.scheduler.mvp.ui.calendar.day.DayMvpView
import ru.cft.shift.scheduler.mvp.ui.calendar.day.DayView
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

        showCalendar(Calendar.getInstance())
    }

    override fun createPresenter() = CalendarPresenter()

    @SuppressLint("SetTextI18n")
    override fun showCalendar(date: Calendar) {
        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)

        date.set(year, month, 1)
        val dayOfWeek = (date.get(Calendar.DAY_OF_WEEK) - 2) % 7

        date.add(Calendar.DAY_OF_YEAR, -dayOfWeek)
        val firstWeek = date.get(Calendar.WEEK_OF_YEAR) - 1

        for (row in 1..5) {
            val rowView = binding.calendarLayout[row] as TableRow
            val weekView = rowView[0] as TextView

            for (column in 1..7) {
                val dayView = rowView[column] as DayMvpView
                dayView.updateDayNumber(date.get(Calendar.DAY_OF_MONTH))

                date.add(Calendar.DAY_OF_YEAR, 1)
            }

            weekView.text = (firstWeek + row - 1).toString()
        }

        binding.month.text = "$year ${DateFormatSymbols().months[month]}"
    }

    override fun showSettingsFragment() {
        // TODO убрать заглушку и сделать
        println("Show Settings")
    }
}