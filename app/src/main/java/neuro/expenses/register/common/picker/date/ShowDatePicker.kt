package neuro.expenses.register.common.picker.date

import androidx.fragment.app.FragmentActivity

interface ShowDatePicker {
  fun showDatePicker(activity: FragmentActivity, onSetDate: OnSetDate)
}

interface OnSetDate {
  fun onSetDate(day: Int, month: Int, year: Int)
}
