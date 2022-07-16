package ru.cft.shift.scheduler.ui.calendar.event

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.data.Mark
import ru.cft.shift.scheduler.databinding.ItemEventBinding
import ru.cft.shift.scheduler.dto.EventColor
import ru.cft.shift.scheduler.ui.calendar.CalendarActivity
import java.text.SimpleDateFormat
import kotlin.math.abs

class EventView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), EventMvpView {

    private companion object {
        val TIME_FORMAT = SimpleDateFormat("HH:mm")
        val DATE_FORMAT = SimpleDateFormat("MM-dd HH:mm")
        const val MINUTE_MILLIS = 60000
        const val DAY_MILLIS = 86400000
    }

    private val binding = ItemEventBinding.inflate(LayoutInflater.from(context), this)

    private var event: Event? = null

    val presenter = EventPresenter()

    init {
        presenter.attachView(this)
    }

    override fun update(event: Event) {
        this.event = event
        val mark = createMarkByEventColor(event.color)

        binding.name.text = event.name
        binding.time.text = if (abs(event.end.time - event.begin.time) < MINUTE_MILLIS) {
            TIME_FORMAT.format(event.begin)
        } else if (abs(event.end.time - event.begin.time) < DAY_MILLIS) {
            "${TIME_FORMAT.format(event.begin)} - ${TIME_FORMAT.format(event.end)}"
        } else {
            "${DATE_FORMAT.format(event.begin)} - ${DATE_FORMAT.format(event.end)}"
        }

        background.setTint(ContextCompat.getColor(context, mark.backgroundColor))
        binding.mark.background.setTint(ContextCompat.getColor(context, mark.defaultColor))

        presenter.eventId = event.id
    }

    override fun attachActionClickListener(listener: (EventMvpPresenter) -> Unit) {
        binding.action.setOnClickListener { listener.invoke(presenter) }
    }

    override fun remove() {
        (parent as ViewGroup).removeView(this)
        event?.let { (context as CalendarActivity).presenter.removeEvent(it) }
    }

    private fun createMarkByEventColor(eventColor: EventColor): Mark {
        return when(eventColor) {
            EventColor.GRAY -> Mark(R.color.mark_light_common, R.color.mark_dark_common)
            EventColor.RED -> Mark(R.color.mark_light_red, R.color.mark_dark_red)
            EventColor.BLUE -> Mark(R.color.mark_light_blue, R.color.mark_dark_blue)
            EventColor.GREEN -> Mark(R.color.mark_light_green, R.color.mark_dark_green)
            EventColor.VIOLET -> Mark(R.color.mark_light_violet, R.color.mark_dark_violet)
            EventColor.YELLOW -> Mark(R.color.mark_light_yellow, R.color.mark_dark_yellow)
        }
    }
}