package neuro.expenses.register.viewmodel.main

import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider

class MainViewModel(
  private val feedLastBillViewModel: FeedLastBillViewModel,
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {
  override fun onCleared() {
    super.onCleared()
    feedLastBillViewModel.observe().baseSubscribe { }
  }
}