package neuro.expenses.register.presentation.common.back

import android.app.Activity

class FinishActivityHandler(private val activity: Activity?) : () -> Unit {
  override fun invoke() {
    activity?.finish()
  }
}