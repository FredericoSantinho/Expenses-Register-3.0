package neuro.expenses.register.ui.manual.register

import androidx.compose.runtime.Composable
import neuro.expenses.register.common.android.fragment.BaseComposeFragment
import neuro.expenses.register.ui.manual.register.composable.ManualRegisterComposable
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManualRegisterFragment : BaseComposeFragment() {

  val manualRegisterViewModel: ManualRegisterViewModel by viewModel()

  @Composable
  override fun getComposable() {
    return ManualRegisterComposable(
      manualRegisterViewModel, requireActivity()
    )
  }
}