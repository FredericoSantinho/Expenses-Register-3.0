package neuro.expenses.register.viewmodel.main

import neuro.expenses.register.viewmodel.bill.FeedLastBillViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.edit.category.EditCategoryViewModel
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductViewModel

class MainViewModel(
  private val feedLastBillViewModel: FeedLastBillViewModel,
  private val editPlaceProductViewModel: EditPlaceProductViewModel,
  private val editCategoryViewModel: EditCategoryViewModel,
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {
  init {
    feedLastBillViewModel.observe().baseSubscribe { }
  }

  override fun onCleared() {
    super.onCleared()
    editPlaceProductViewModel.onCleared()
    editCategoryViewModel.onCleared()
  }
}