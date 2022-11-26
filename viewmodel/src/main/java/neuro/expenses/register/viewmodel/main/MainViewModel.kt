package neuro.expenses.register.viewmodel.main

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.fab.FloatingActionButtonViewModel

class MainViewModel(schedulerProvider: SchedulerProvider) : BaseViewModel(schedulerProvider) {
  val appBarViewModel = mutableStateOf(AppBarViewModel())
  val floatingActionButtonViewModel = mutableStateOf<FloatingActionButtonViewModel?>(null)

  fun reset() {
    appBarViewModel.value = AppBarViewModel()
    floatingActionButtonViewModel.value = null
  }
}