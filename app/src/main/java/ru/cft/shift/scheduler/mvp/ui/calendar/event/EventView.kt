package ru.cft.shift.scheduler.mvp.ui.calendar.event

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import ru.cft.shift.scheduler.databinding.ItemEventBinding
import ru.cft.shift.scheduler.mvp.data.Mark

class EventView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), EventMvpView {

    private val binding = ItemEventBinding.inflate(LayoutInflater.from(context), this)

    val presenter = EventPresenter()

    init {
        presenter.attachView(this)
    }

    override fun updateMark(mark: Mark) {
        background.setTint(ContextCompat.getColor(context, mark.backgroundColor))
        binding.mark.background.setTint(ContextCompat.getColor(context, mark.defaultColor))
    }

    override fun attachActionClickListener(listener: (EventMvpPresenter) -> Unit) {
        binding.action.setOnClickListener { listener.invoke(presenter) }
    }
}