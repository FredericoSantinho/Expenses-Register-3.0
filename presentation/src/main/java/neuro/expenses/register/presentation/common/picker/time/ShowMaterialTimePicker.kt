package neuro.expenses.register.presentation.common.picker.time

import androidx.fragment.app.FragmentActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class ShowMaterialTimePicker : ShowTimePicker {
  override fun showTimePicker(activity: FragmentActivity, onSetTime: OnSetTime) {
    val cldr: Calendar = Calendar.getInstance()
    val hour: Int = cldr.get(Calendar.HOUR_OF_DAY)
    val minutes: Int = cldr.get(Calendar.MINUTE)
    showTimePicker(activity, onSetTime, hour, minutes)
  }

  override fun showTimePicker(
    activity: FragmentActivity,
    onSetTime: OnSetTime,
    hour: Int,
    minutes: Int
  ) {
    val timePicker = MaterialTimePicker
      .Builder()
      .setTimeFormat(TimeFormat.CLOCK_24H)
      .setHour(hour)
      .setMinute(minutes)
      .build()
    timePicker
      .show(activity.supportFragmentManager, "TIME_PICKER")
  }
}