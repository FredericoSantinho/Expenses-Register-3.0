package neuro.expenses.register

import android.os.Bundle
import android.view.WindowManager
import androidx.compose.runtime.Composable
import neuro.expenses.register.common.activity.BaseComposeActivity
import neuro.expenses.register.ui.main.MainComposable

class MainActivity : BaseComposeActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    @Suppress("DEPRECATION")
    // We need to use this method as our minSdk is < 30.
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
  }

  @Composable
  override fun getComposable() {
    return MainComposable(this)
  }
}