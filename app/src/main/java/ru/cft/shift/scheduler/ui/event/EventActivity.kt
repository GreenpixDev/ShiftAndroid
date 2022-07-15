package ru.cft.shift.scheduler.ui.event

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.cft.shift.scheduler.R
import ru.cft.shift.scheduler.databinding.*
import ru.cft.shift.scheduler.ui.base.BaseActivity
import javax.inject.Inject

class EventActivity : BaseActivity<EventMvpPresenter>(), EventMvpView {

    private lateinit var binding: ActivityEventBinding
    private lateinit var bindingBottomSheetEventType: BottomSheetEventTypeBinding
    private lateinit var bindingBottomSheetDataPickerStart: BottomSheetDataPickerStartBinding
    private lateinit var bindingBottomSheetDataPickerFinish: BottomSheetDataPickerFinishBinding
    private lateinit var bindingBottomSheetColor: BottomSheetColorBinding

    @Inject
    override lateinit var presenter: EventMvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        binding.closeButton.setOnClickListener { closeActivity() }

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
            binding.type.setText(choosenType.text.toString())
            bottomSheetDialog.dismiss()
        }

        bindingBottomSheetEventType.buttonCancel.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        val str = arrayOf<String>("СР, 6 Июля","ЧТ, 7 Июля", "ПТ, 8 Июля")

        bindingBottomSheetDataPickerStart.data.minValue = 0
        bindingBottomSheetDataPickerStart.data.maxValue = str.size - 1
        bindingBottomSheetDataPickerStart.data.displayedValues = str

        bindingBottomSheetDataPickerStart.hour.minValue = 0
        bindingBottomSheetDataPickerStart.hour.maxValue = 23

        bindingBottomSheetDataPickerStart.minute.minValue = 0
        bindingBottomSheetDataPickerStart.minute.maxValue = 59

        var date = "СР, 6 июля"
        var hour = "00"
        var minute = "00"
        var year = 2022

        bindingBottomSheetDataPickerStart.data.setOnValueChangedListener { numberPicker, i, i2 ->
            val i = numberPicker.value
            date = str[i]

            bindingBottomSheetDataPickerStart.liveDate.setText(date + ' ' + year.toString()  + ' ' + hour + ':' + minute)
        }

        bindingBottomSheetDataPickerStart.hour.setOnValueChangedListener { numberPicker, i, i2 ->
            hour = numberPicker.value.toString()

            if (hour.length == 1) {
                hour = '0' + hour
            }

            bindingBottomSheetDataPickerStart.liveDate.setText(date + ' ' + year.toString()  + ' ' + hour + ':' + minute)
        }

        bindingBottomSheetDataPickerStart.minute.setOnValueChangedListener { numberPicker, i, i2 ->
            minute = numberPicker.value.toString()

            if (minute.length == 1){
                minute = '0' + minute
            }

            bindingBottomSheetDataPickerStart.liveDate.setText(date + ' ' + year.toString()  + ' ' + hour + ':' + minute)
        }

        bindingBottomSheetDataPickerStart.buttonOK.setOnClickListener {
            binding.timeStart.setText(bindingBottomSheetDataPickerStart.liveDate.text)
            bottomSheetDataPickerStart.dismiss()
        }

        bindingBottomSheetDataPickerStart.buttonCancel.setOnClickListener {
            bottomSheetDataPickerStart.dismiss()
        }

        val str2 = arrayOf<String>("СР, 6 Июля","ЧТ, 7 Июля", "ПТ, 8 Июля")

        bindingBottomSheetDataPickerFinish.data.minValue = 0
        bindingBottomSheetDataPickerFinish.data.maxValue = str2.size - 1
        bindingBottomSheetDataPickerFinish.data.displayedValues = str2

        bindingBottomSheetDataPickerFinish.hour.minValue = 0
        bindingBottomSheetDataPickerFinish.hour.maxValue = 23

        bindingBottomSheetDataPickerFinish.minute.minValue = 0
        bindingBottomSheetDataPickerFinish.minute.maxValue = 59

        var date2 = "СР, 6 июля"
        var hour2 = "00"
        var minute2 = "00"
        var year2 = 2022

        bindingBottomSheetDataPickerFinish.data.setOnValueChangedListener { numberPicker, i, i2 ->
            val i = numberPicker.value
            date2 = str2[i]

            bindingBottomSheetDataPickerFinish.liveDate.setText(date2 + ' ' + year2.toString()  + ' ' + hour2 + ':' + minute2)

        }

        bindingBottomSheetDataPickerFinish.hour.setOnValueChangedListener { numberPicker, i, i2 ->
            hour2 = numberPicker.value.toString()

            if (hour2.length == 1){
                hour2 = '0' + hour2
            }

            bindingBottomSheetDataPickerFinish.liveDate.setText(date2 + ' ' + year2.toString()  + ' ' + hour2 + ':' + minute2)
        }

        bindingBottomSheetDataPickerFinish.minute.setOnValueChangedListener { numberPicker, i, i2 ->
            minute2 = numberPicker.value.toString()

            if (minute2.length == 1){
                minute2 = '0' + minute2
            }

            bindingBottomSheetDataPickerFinish.liveDate.setText(date2 + ' ' + year2.toString()  + ' ' + hour2 + ':' + minute2)
        }

        bindingBottomSheetDataPickerFinish.buttonOK.setOnClickListener {
            binding.timeFinish.setText(bindingBottomSheetDataPickerFinish.liveDate.text)
            bottomSheetDataPickerFinish.dismiss()
        }

        bindingBottomSheetDataPickerStart.buttonCancel.setOnClickListener {
            bottomSheetDataPickerFinish.dismiss()
        }

        var choosenColor = bindingBottomSheetColor.checkBox

        bindingBottomSheetColor.checkBox.setOnClickListener {
            if (bindingBottomSheetColor.checkBox.isChecked){
                bindingBottomSheetColor.checkBox2.isChecked = false
                bindingBottomSheetColor.checkBox3.isChecked = false
                bindingBottomSheetColor.buttonOK.setBackgroundColor(getResources().getColor(R.color.red))
                choosenColor = bindingBottomSheetColor.checkBox
            }

            else{
                bindingBottomSheetColor.buttonOK.setBackgroundColor(Color.parseColor("#42AAFF"))
            }
        }

        bindingBottomSheetColor.checkBox2.setOnClickListener {
            if (bindingBottomSheetColor.checkBox2.isChecked){
                bindingBottomSheetColor.checkBox.isChecked = false
                bindingBottomSheetColor.checkBox3.isChecked = false
                bindingBottomSheetColor.buttonOK.setBackgroundColor(getResources().getColor(R.color.green))

                choosenColor = bindingBottomSheetColor.checkBox2
            }

            else{
                bindingBottomSheetColor.buttonOK.setBackgroundColor(getResources().getColor(R.color.blue))
                choosenColor = bindingBottomSheetColor.checkBox
            }
        }

        bindingBottomSheetColor.checkBox3.setOnClickListener {
            if (bindingBottomSheetColor.checkBox3.isChecked){
                bindingBottomSheetColor.checkBox.isChecked = false
                bindingBottomSheetColor.checkBox2.isChecked = false
                bindingBottomSheetColor.buttonOK.setBackgroundColor(getResources().getColor(R.color.berlin_lazur))

                choosenColor = bindingBottomSheetColor.checkBox3
            }

            else{
                bindingBottomSheetColor.buttonOK.setBackgroundColor(getResources().getColor(R.color.blue))
                choosenColor = bindingBottomSheetColor.checkBox
            }
        }

        bindingBottomSheetColor.buttonOK.setOnClickListener {
            binding.colour.setText(choosenColor.text)

            when (choosenColor) {
                bindingBottomSheetColor.checkBox -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(getResources().getColor(
                    R.color.red
                ))
                bindingBottomSheetColor.checkBox2 -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(getResources().getColor(
                    R.color.green
                ))
                bindingBottomSheetColor.checkBox3 -> binding.colorCircle.backgroundTintList = ColorStateList.valueOf(getResources().getColor(
                    R.color.berlin_lazur
                ))
            }

            bottomSheetColor.dismiss()
        }

        bindingBottomSheetColor.buttonCancel.setOnClickListener {
            bottomSheetColor.dismiss()
        }
    }

    private fun closeActivity() {
        finish()
    }

}