package neuro.expenses.register.common.picker.date

import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class ShowMaterialDatePicker : ShowDatePicker {
  override fun showDatePicker(activity: FragmentActivity, onSetDate: OnSetDate) {
    val picker = MaterialDatePicker.Builder.datePicker().build()
    activity.let {
      picker.show(it.supportFragmentManager, picker.toString())
      picker.addOnPositiveButtonClickListener {
        val calendar = Calendar.getInstance()
        calendar.time = Date(it)

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)

        onSetDate.onSetDate(day, month, year)
      }
    }
  }
}