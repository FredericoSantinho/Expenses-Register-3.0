package neuro.expenses.register

import android.os.Bundle
import android.view.WindowManager
import androidx.compose.runtime.Composable
import neuro.expenses.register.common.activity.BaseComposeActivity
import neuro.expenses.register.ui.main.MainComposable
import neuro.expenses.register.viewmodel.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseComposeActivity() {

  private val mainViewModel: MainViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    @Suppress("DEPRECATION")
    // We need to use this method as our minSdk is < 30.
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    // We need to reference the view model in order for it to be bounded to the activity's lifecycle.
    mainViewModel
  }

  @Composable
  override fun getComposable() {
    return MainComposable(this)
  }
}