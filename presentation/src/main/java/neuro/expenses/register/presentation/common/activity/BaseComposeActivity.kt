package neuro.expenses.register.presentation.common.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme

abstract class BaseComposeActivity : BaseNavActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      ExpensesRegisterTheme {
        getComposable()
      }
    }
  }

  @Composable
  protected abstract fun getComposable()
}