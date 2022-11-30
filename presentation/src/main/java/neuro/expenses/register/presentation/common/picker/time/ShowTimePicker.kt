package neuro.expenses.register.presentation.common.picker.time

import androidx.fragment.app.FragmentActivity

interface ShowTimePicker {
  fun showTimePicker(activity: FragmentActivity, onSetTime: OnSetTime)
  fun showTimePicker(activity: FragmentActivity, onSetTime: OnSetTime, hour: Int, minutes: Int)
}

interface OnSetTime {
  fun onSetTime(hour: Int, minute: Int)
}