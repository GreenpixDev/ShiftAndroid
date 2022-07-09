package ru.cft.shift.scheduler.mvp.ui.calendar.day

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.databinding.ItemDayBinding

class DayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), DayMvpView {

    private val binding = ItemDayBinding.inflate(LayoutInflater.from(context), this)

    private val presenter = DayPresenter()

    init {
        presenter.attachView(this)
    }

    override fun updateDayNumber(day: Int) {
        binding.number.text = day.toString()
    }

    override fun updateIsWeekend(value: Boolean) {
        val color = ContextCompat.getColor(context, if (value) R.color.red else R.color.black)
        binding.number.setTextColor(color)
    }

    override fun updateIsCurrentMonth(value: Boolean) {
        binding.number.alpha = if (value) 1f else .3f
    }
}