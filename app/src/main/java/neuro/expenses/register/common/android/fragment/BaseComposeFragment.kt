package neuro.expenses.register.common.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme

abstract class BaseComposeFragment : BaseNavFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return ComposeView(requireContext()).apply {
      setContent {
        ExpensesRegisterTheme {
          getComposable()
        }
      }
    }
  }

  @Composable
  protected abstract fun getComposable()
}