package neuro.expenses.register.viewmodel.scaffold

import androidx.compose.runtime.MutableState
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.fab.FloatingActionButtonViewModel

interface ScaffoldViewModelState {
  val appBarViewModel: MutableState<AppBarViewModel>
  val floatingActionButtonViewModel: MutableState<FloatingActionButtonViewModel?>

  fun reset()
}