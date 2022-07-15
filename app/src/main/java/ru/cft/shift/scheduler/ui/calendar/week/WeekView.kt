package ru.cft.shift.scheduler.ui.calendar.week

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TableRow
import androidx.core.view.get
import ru.cft.shift.scheduler.databinding.ItemWeekBinding
import ru.cft.shift.scheduler.ui.calendar.day.DayMvpPresenter
import ru.cft.shift.scheduler.ui.calendar.day.DayView
import java.util.*

class WeekView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : TableRow(context, attrs), WeekMvpView {

    private val binding = ItemWeekBinding.inflate(LayoutInflater.from(context), this)

    private lateinit var dayClickListener: (DayMvpPresenter) -> Unit

    val presenter = WeekPresenter()

    init {
        presenter.attachView(this)
    }

    override fun attachDayClickListener(listener: (DayMvpPresenter) -> Unit) {
        dayClickListener = listener
    }

    override fun updateWeekNumber(week: Int) {
        binding.number.text = week.toString()
    }

    override fun updateDaysFrom(month: Int, firstWeekDay: Calendar) {
        val date = firstWeekDay.clone() as Calendar

        for (column in 1..7) {
            val dayView = get(column) as DayView

            presenter.dayPresenters.add(dayView.presenter)
            dayView.presenter.attachDay(date)
            dayView.attachClickListener(dayClickListener)

            dayView.updateDayNumber(date.get(Calendar.DAY_OF_MONTH))
            dayView.updateIsWeekend(column == 7)
            dayView.updateIsCurrentMonth(date.get(Calendar.MONTH) == month)

            date.add(Calendar.DAY_OF_YEAR, 1)
        }
    }
}