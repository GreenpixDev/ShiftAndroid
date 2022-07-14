package ru.cft.shift.scheduler.ui.calendar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.core.view.get
import com.airbnb.paris.extensions.style
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.databinding.ActivityCalendarBinding
import ru.cft.shift.scheduler.di.component.DaggerApplicationComponent
import ru.cft.shift.scheduler.ui.base.BaseActivity
import ru.cft.shift.scheduler.ui.calendar.event.EventMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.event.EventView
import ru.cft.shift.scheduler.ui.calendar.event.modal.EventModalFragment
import ru.cft.shift.scheduler.ui.calendar.settings.SettingsFragment
import ru.cft.shift.scheduler.ui.calendar.week.WeekView
import java.text.DateFormatSymbols
import java.util.*
import javax.inject.Inject

class CalendarActivity : BaseActivity<CalendarMvpPresenter>(), CalendarMvpView {

    private lateinit var binding: ActivityCalendarBinding

    @Inject override lateinit var presenter: CalendarMvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerApplicationComponent.create().inject(this)

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
                val weekView = binding.calendarLayout[row] as WeekView
                val weekNumber = date.get(Calendar.WEEK_OF_YEAR)

                presenter.weekPresenters.add(weekView.presenter)
                weekView.attachDayClickListener { presenter.onDayClick(it) }

                weekView.updateWeekNumber(weekNumber)
                weekView.updateDaysFrom(month, date)
            }
            date.add(Calendar.DAY_OF_YEAR, 7)
        }
        binding.month.text = "$year ${DateFormatSymbols().months[month]}"

        presenter.attachYearAndMonth(year, month)
    }

    override fun showCalendar(year: Int, month: Int) {
        val intent = Intent(this, CalendarActivity::class.java).apply {
            putExtra(MESSAGE_YEAR, year)
            putExtra(MESSAGE_MONTH, month)
        }
        startActivity(intent)
    }

    override fun showSettingsModalWindow() {
        val fragment = SettingsFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.modal_window, fragment)
        transaction.commit()
    }

    override fun showEventModalFragment(presenter: EventMvpPresenter) {
        val fragment = EventModalFragment(presenter)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.modal_window, fragment)
        transaction.commit()
    }

    override fun hideModalWindow() {
        val fragment = supportFragmentManager.findFragmentById(R.id.modal_window)
        fragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(it)
            transaction.commit()
        }
    }

    override fun clearEventViews() {
        while (binding.eventList.childCount > 0) {
            binding.eventList.removeViewAt(0)
        }
    }

    override fun addEventView(event: Event) {
        val eventView = EventView(this)
        eventView.style(R.style.Calendar_Event)
        eventView.updateMark(event.mark)
        eventView.attachActionClickListener { showEventModalFragment(it) }

        binding.eventList.addView(eventView)
    }

    private companion object {

        const val MESSAGE_YEAR = "year"
        const val MESSAGE_MONTH = "month"

    }
}