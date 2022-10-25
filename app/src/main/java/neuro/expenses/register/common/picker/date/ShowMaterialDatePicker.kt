package neuro.expenses.register.common.picker.date

import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.MaterialDatePicker

class ShowMaterialDatePicker : ShowDatePicker {
  override fun showDatePicker(activity: FragmentActivity, onSetDate: OnSetDate) {
    val picker = MaterialDatePicker.Builder.datePicker().build()
    activity.let {
      picker.show(it.supportFragmentManager, picker.toString())
      picker.addOnPositiveButtonClickListener {
        onSetDate.onSetDate(it)
      }
    }
  }
}