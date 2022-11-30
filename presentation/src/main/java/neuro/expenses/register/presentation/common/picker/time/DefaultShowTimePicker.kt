package neuro.expenses.register.presentation.common.picker.time

import android.app.TimePickerDialog
import androidx.fragment.app.FragmentActivity
import java.util.*

class DefaultShowTimePicker : ShowTimePicker {
  override fun showTimePicker(activity: FragmentActivity, onSetTime: OnSetTime) {
    val calendar: Calendar = Calendar.getInstance()
    val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes: Int = calendar.get(Calendar.MINUTE)
    showTimePicker(activity, onSetTime, hour = hour, minutes = minutes)
  }

  override fun showTimePicker(
    activity: FragmentActivity, onSetTime: OnSetTime, hour: Int, minutes: Int
  ) {
    val picker = TimePickerDialog(
      activity,
      { _, sHour, sMinute -> onSetTime.onSetTime(sHour, sMinute) }, hour, minutes, true
    )
    picker.show()
  }
}