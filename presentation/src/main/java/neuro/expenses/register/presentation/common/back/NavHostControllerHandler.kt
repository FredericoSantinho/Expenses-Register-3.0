package neuro.expenses.register.presentation.common.back

import android.app.Activity
import androidx.navigation.NavHostController

class NavHostControllerHandler(
  private val navHostController: NavHostController,
  private val activity: Activity?
) : () -> Unit {
  override fun invoke() {
    if (navHostController.backQueue.size == 1) {
      navHostController.popBackStack()
    } else {
      activity?.finish()
    }
  }
}