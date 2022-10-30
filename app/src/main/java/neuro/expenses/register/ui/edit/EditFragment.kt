package neuro.expenses.register.ui.edit

import androidx.compose.runtime.Composable
import neuro.expenses.register.common.android.BaseComposeFragment
import neuro.expenses.register.ui.edit.composable.EditComposable

class EditFragment : BaseComposeFragment() {
  @Composable
  override fun getComposable() {
    return EditComposable()
  }
}