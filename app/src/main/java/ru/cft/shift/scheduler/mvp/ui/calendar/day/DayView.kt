package ru.cft.shift.scheduler.mvp.ui.calendar.day

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
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
        binding.text.text = day.toString()
    }
}