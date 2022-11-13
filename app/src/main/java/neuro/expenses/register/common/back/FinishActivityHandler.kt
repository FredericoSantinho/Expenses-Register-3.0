package neuro.expenses.register.common.back

import android.app.Activity

class FinishActivityHandler(private val activity: Activity?) : () -> Unit {
  override fun invoke() {
    activity?.finish()
  }
}