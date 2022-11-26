package neuro.expenses.register.viewmodel.scaffold

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.fab.FloatingActionButtonViewModel

class ScaffoldViewModelStateImpl : ScaffoldViewModelState {
  override val appBarViewModel = mutableStateOf(AppBarViewModel())
  override val floatingActionButtonViewModel = mutableStateOf<FloatingActionButtonViewModel?>(null)

  override fun reset() {
    appBarViewModel.value = AppBarViewModel()
    floatingActionButtonViewModel.value = null
  }
}