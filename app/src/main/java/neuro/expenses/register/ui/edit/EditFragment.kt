package neuro.expenses.register.ui.edit

import androidx.compose.runtime.Composable
import neuro.expenses.register.common.fragment.BaseComposeFragment

class EditFragment : BaseComposeFragment() {
  @Composable
  override fun getComposable() {
    return EditComposable()
  }
}