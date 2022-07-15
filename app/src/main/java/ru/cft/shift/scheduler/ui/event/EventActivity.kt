package ru.cft.shift.scheduler.ui.event

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.data.Day
import ru.cft.shift.scheduler.data.Event
import ru.cft.shift.scheduler.data.Month
import ru.cft.shift.scheduler.databinding.*
import ru.cft.shift.scheduler.dto.EventColor
import ru.cft.shift.scheduler.dto.EventType
import ru.cft.shift.scheduler.ui.base.BaseActivity
import ru.cft.shift.scheduler.ui.calendar.CalendarActivity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventActivity : BaseActivity<EventMvpPresenter>(), EventMvpView {

    companion object {
        const val MESSAGE_EVENT_ID = "eventId"
        val DATE_FORMAT = SimpleDateFormat("E, d MMM")
        val DATE_TIME_FORMAT = SimpleDateFormat("E, d MMM yyyy HH:mm")
    }

    private lateinit var binding: ActivityEventBinding
    private lateinit var bindingBottomSheetEventType: BottomSheetEventTypeBinding
    private lateinit var bindingBottomSheetDataPickerStart: BottomSheetDataPickerStartBinding
    private lateinit var bindingBottomSheetDataPickerFinish: BottomSheetDataPickerFinishBinding
    private lateinit var bindingBottomSheetColor: BottomSheetColorBinding

    @Inject
    override lateinit var presenter: EventMvpPresenter

    override var day: Day? = null

    private lateinit var month: Month

    private val startDate = Calendar.getInstance()
    private val finishDate = Calendar.getInstance()
    private val newStartDate = Calendar.getInstance()
    private val newFinishDate = Calendar.getInstance()

    private val dayList = mutableListOf<String>()

    override val eventId: Long?
        get() = if (intent.hasExtra(MESSAGE_EVENT_ID)) {
            intent.getLongExtra(MESSAGE_EVENT_ID, 0L)
        } else {
            null
        }

    override fun update(event: Event) {
        startDate.time = event.begin
        finishDate.time = event.end

        if (event.name.isNotBlank()) {
            binding.eventName.setText(event.name)
        }
        binding.timeStart.text = DATE_TIME_FORMAT.format(event.begin)
        binding.timeFinish.text = DATE_TIME_FORMAT.format(event.end)

        bindingBottomSheetDataPickerStart.data.value = startDate.get(Calendar.DAY_OF_MONTH) - 1
        bindingBottomSheetDataPickerStart.hour.value = startDate.get(Calendar.HOUR_OF_DAY)
        bindingBottomSheetDataPickerStart.minute.value = startDate.get(Calendar.MINUTE)
        bindingBottomSheetDataPickerStart.liveDate.text = DATE_TIME_FORMAT.format(startDate.time)

        bindingBottomSheetDataPickerFinish.data.value = finishDate.get(Calendar.DAY_OF_MONTH) - 1
        bindingBottomSheetDataPickerFinish.hour.value = finishDate.get(Calendar.HOUR_OF_DAY)
        bindingBottomSheetDataPickerFinish.minute.value = finishDate.get(Calendar.MINUTE)
        bindingBottomSheetDataPickerFinish.liveDate.text = DATE_TIME_FORMAT.format(finishDate.time)

        when (event.color){
            EventColor.YELLOW -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(
                resources.getColor(
                R.color.mark_light_yellow))
            EventColor.BLUE -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(
                resources.getColor(
                R.color.blue))
            EventColor.GRAY -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(
                resources.getColor(
                R.color.light_gray))
            EventColor.GREEN -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(
                resources.getColor(
                R.color.green))
            EventColor.RED -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(
                resources.getColor(
                R.color.red))
            EventColor.VIOLET -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(
                resources.getColor(
                R.color.mark_dark_violet))
        }

        /*when (event.type) { TODO
            EventType.EVENT -> binding.type.setText(R.string.event)
            EventType.GOAL ->  binding.type.setText(R.string.goal)
            EventType.MEETING -> binding.type.setText(R.string.meeting)
        }*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        month = Month(
            monthNumber = intent.getIntExtra(CalendarActivity.MESSAGE_MONTH, 0),
            yearNumber = intent.getIntExtra(CalendarActivity.MESSAGE_YEAR, 2000)
        )

        if (intent.hasExtra(CalendarActivity.MESSAGE_DAY)) {
            day = Day(
                monthNumber = month.monthNumber,
                yearNumber = month.yearNumber,
                dayNumber = intent.getIntExtra(CalendarActivity.MESSAGE_DAY, 1)
            )
        }

        binding = ActivityEventBinding.inflate(layoutInflater)
        bindingBottomSheetEventType = BottomSheetEventTypeBinding.inflate(layoutInflater)
        bindingBottomSheetDataPickerStart = BottomSheetDataPickerStartBinding.inflate(layoutInflater)
        bindingBottomSheetDataPickerFinish = BottomSheetDataPickerFinishBinding.inflate(layoutInflater)
        bindingBottomSheetColor = BottomSheetColorBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetDataPickerStart = BottomSheetDialog(this)
        val bottomSheetDataPickerFinish = BottomSheetDialog(this)
        val bottomSheetColor = BottomSheetDialog(this)

        bottomSheetDialog.setContentView(bindingBottomSheetEventType.root)
        bottomSheetDataPickerStart.setContentView(bindingBottomSheetDataPickerStart.root)
        bottomSheetDataPickerFinish.setContentView(bindingBottomSheetDataPickerFinish.root)
        bottomSheetColor.setContentView(bindingBottomSheetColor.root)

        binding.type.setOnClickListener {
            bottomSheetDialog.show()
        }

        binding.timeStart.setOnClickListener {
            bottomSheetDataPickerStart.show()
        }

        binding.timeFinish.setOnClickListener {
            bottomSheetDataPickerFinish.show()
        }

        binding.colour.setOnClickListener {
            bottomSheetColor.show()
        }

        binding.closeButton.setOnClickListener { showCalendarScreen() }
        binding.checkmarkButton.setOnClickListener { onCreateOrUpdateEvent() }

        var choosenType = bindingBottomSheetEventType.checkBox

        bindingBottomSheetEventType.checkBox.setOnClickListener {
            if (bindingBottomSheetEventType.checkBox.isChecked) {
                bindingBottomSheetEventType.checkBox2.isChecked = false
                bindingBottomSheetEventType.checkBox3.isChecked = false
            }
            choosenType = bindingBottomSheetEventType.checkBox
        }

        bindingBottomSheetEventType.checkBox2.setOnClickListener {
            if (bindingBottomSheetEventType.checkBox2.isChecked) {
                bindingBottomSheetEventType.checkBox.isChecked = false
                bindingBottomSheetEventType.checkBox3.isChecked = false
                choosenType = bindingBottomSheetEventType.checkBox2
            }

            else {
                choosenType = bindingBottomSheetEventType.checkBox
            }
        }

        bindingBottomSheetEventType.checkBox3.setOnClickListener {
            if (bindingBottomSheetEventType.checkBox3.isChecked){
                bindingBottomSheetEventType.checkBox.isChecked = false
                bindingBottomSheetEventType.checkBox2.isChecked = false
                choosenType = bindingBottomSheetEventType.checkBox3
            }

            else {
                choosenType = bindingBottomSheetEventType.checkBox
            }
        }

        bindingBottomSheetEventType.buttonOK.setOnClickListener {
            binding.type.text = choosenType.text.toString()
            bottomSheetDialog.dismiss()
        }

        bindingBottomSheetEventType.buttonCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        val calDate = month.calendar
        while (calDate.get(Calendar.MONTH) == month.monthNumber) {
            dayList.add(DATE_FORMAT.format(calDate.time))
            calDate.add(Calendar.DAY_OF_YEAR, 1)
        }

        bindingBottomSheetDataPickerStart.data.minValue = 0
        bindingBottomSheetDataPickerStart.data.maxValue = dayList.size - 1
        bindingBottomSheetDataPickerStart.data.displayedValues = dayList.toTypedArray()

        bindingBottomSheetDataPickerStart.hour.minValue = 0
        bindingBottomSheetDataPickerStart.hour.maxValue = 23

        bindingBottomSheetDataPickerStart.minute.minValue = 0
        bindingBottomSheetDataPickerStart.minute.maxValue = 59

        // Дата начала
        bindingBottomSheetDataPickerStart.data.setOnValueChangedListener { numberPicker, _, _ ->
            newStartDate.set(Calendar.DAY_OF_MONTH, numberPicker.value + 1)
            bindingBottomSheetDataPickerStart.liveDate.text = DATE_TIME_FORMAT.format(newStartDate.time)
        }

        bindingBottomSheetDataPickerStart.hour.setOnValueChangedListener { numberPicker, _, _ ->
            newStartDate.set(Calendar.HOUR_OF_DAY, numberPicker.value)
            bindingBottomSheetDataPickerStart.liveDate.text = DATE_TIME_FORMAT.format(newStartDate.time)
        }

        bindingBottomSheetDataPickerStart.minute.setOnValueChangedListener { numberPicker, _, _ ->
            newStartDate.set(Calendar.MINUTE, numberPicker.value)
            bindingBottomSheetDataPickerStart.liveDate.text = DATE_TIME_FORMAT.format(newStartDate.time)
        }

        bindingBottomSheetDataPickerStart.buttonOK.setOnClickListener {
            binding.timeStart.text = bindingBottomSheetDataPickerStart.liveDate.text
            startDate.time = newStartDate.time
            bottomSheetDataPickerStart.dismiss()
        }

        bindingBottomSheetDataPickerStart.buttonCancel.setOnClickListener {
            bottomSheetDataPickerStart.dismiss()
        }

        bindingBottomSheetDataPickerFinish.data.minValue = 0
        bindingBottomSheetDataPickerFinish.data.maxValue = dayList.size - 1
        bindingBottomSheetDataPickerFinish.data.displayedValues = dayList.toTypedArray()

        bindingBottomSheetDataPickerFinish.hour.minValue = 0
        bindingBottomSheetDataPickerFinish.hour.maxValue = 23

        bindingBottomSheetDataPickerFinish.minute.minValue = 0
        bindingBottomSheetDataPickerFinish.minute.maxValue = 59

        // Дата конца
        bindingBottomSheetDataPickerFinish.data.setOnValueChangedListener { numberPicker, _, _ ->
            newFinishDate.set(Calendar.DAY_OF_MONTH, numberPicker.value + 1)
            bindingBottomSheetDataPickerFinish.liveDate.text = DATE_TIME_FORMAT.format(newFinishDate.time)
        }

        bindingBottomSheetDataPickerFinish.hour.setOnValueChangedListener { numberPicker, _, _ ->
            newFinishDate.set(Calendar.HOUR_OF_DAY, numberPicker.value)
            bindingBottomSheetDataPickerFinish.liveDate.text = DATE_TIME_FORMAT.format(newFinishDate.time)
        }

        bindingBottomSheetDataPickerFinish.minute.setOnValueChangedListener { numberPicker, _, _ ->
            newFinishDate.set(Calendar.MINUTE, numberPicker.value)
            bindingBottomSheetDataPickerFinish.liveDate.text = DATE_TIME_FORMAT.format(newFinishDate.time)
        }

        bindingBottomSheetDataPickerFinish.buttonOK.setOnClickListener {
            binding.timeFinish.text = bindingBottomSheetDataPickerFinish.liveDate.text
            finishDate.time = newFinishDate.time
            bottomSheetDataPickerFinish.dismiss()
        }

        bindingBottomSheetDataPickerStart.buttonCancel.setOnClickListener {
            bottomSheetDataPickerFinish.dismiss()
        }

        var chosenColor = bindingBottomSheetColor.checkBox

        bindingBottomSheetColor.checkBox.setOnClickListener {
            if (bindingBottomSheetColor.checkBox.isChecked){
                bindingBottomSheetColor.checkBox2.isChecked = false
                bindingBottomSheetColor.checkBox3.isChecked = false
                bindingBottomSheetColor.buttonOK.setBackgroundColor(resources.getColor(R.color.red))
                chosenColor = bindingBottomSheetColor.checkBox
            }

            else{
                bindingBottomSheetColor.buttonOK.setBackgroundColor(Color.parseColor("#42AAFF"))
            }
        }

        bindingBottomSheetColor.checkBox2.setOnClickListener {
            if (bindingBottomSheetColor.checkBox2.isChecked){
                bindingBottomSheetColor.checkBox.isChecked = false
                bindingBottomSheetColor.checkBox3.isChecked = false
                bindingBottomSheetColor.buttonOK.setBackgroundColor(resources.getColor(R.color.green))

                chosenColor = bindingBottomSheetColor.checkBox2
            }

            else{
                bindingBottomSheetColor.buttonOK.setBackgroundColor(resources.getColor(R.color.blue))
                chosenColor = bindingBottomSheetColor.checkBox
            }
        }

        bindingBottomSheetColor.checkBox3.setOnClickListener {
            if (bindingBottomSheetColor.checkBox3.isChecked){
                bindingBottomSheetColor.checkBox.isChecked = false
                bindingBottomSheetColor.checkBox2.isChecked = false
                bindingBottomSheetColor.buttonOK.setBackgroundColor(resources.getColor(R.color.berlin_lazur))

                chosenColor = bindingBottomSheetColor.checkBox3
            }

            else{
                bindingBottomSheetColor.buttonOK.setBackgroundColor(resources.getColor(R.color.blue))
                chosenColor = bindingBottomSheetColor.checkBox
            }
        }

        bindingBottomSheetColor.buttonOK.setOnClickListener {
            binding.colour.text = chosenColor.text

            when (chosenColor) {
                bindingBottomSheetColor.checkBox -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(
                    resources.getColor(
                    R.color.red
                ))
                bindingBottomSheetColor.checkBox2 -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(
                    resources.getColor(
                    R.color.green
                ))
                bindingBottomSheetColor.checkBox3 -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(
                    resources.getColor(
                    R.color.berlin_lazur
                ))
            }

            bottomSheetColor.dismiss()
        }

        bindingBottomSheetColor.buttonCancel.setOnClickListener {
            bottomSheetColor.dismiss()
        }
    }

    override fun showCalendarScreen() {
        val intent = Intent(this, CalendarActivity::class.java)
        intent.putExtra(CalendarActivity.MESSAGE_YEAR, month.yearNumber)
        intent.putExtra(CalendarActivity.MESSAGE_MONTH, month.monthNumber)
        startActivity(intent)
    }

    private fun onCreateOrUpdateEvent() {
        presenter.createOrUpdate(Event(
            id = 0,
            name = binding.eventName.text.toString(),
            begin = startDate.time,
            end = finishDate.time,
            color = EventColor.YELLOW, // TODO убрать заглушку
            type = EventType.EVENT // TODO убрать заглушку
        ))
    }

}