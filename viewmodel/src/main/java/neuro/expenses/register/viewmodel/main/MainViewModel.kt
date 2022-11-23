package neuro.expenses.register.viewmodel.main

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider

class MainViewModel(schedulerProvider: SchedulerProvider) : BaseViewModel(schedulerProvider) {
  val appBarViewModel = mutableStateOf(AppBarViewModel())
}