package neuro.expenses.register.viewmodel.main

import neuro.expenses.register.viewmodel.bill.FeedLastbillCardViewModel
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.edit.category.EditCategoryViewModel
import neuro.expenses.register.viewmodel.edit.placeproduct.EditPlaceProductViewModel

class MainViewModel(
  private val feedLastbillCardViewModel: FeedLastbillCardViewModel,
  private val editPlaceProductViewModel: EditPlaceProductViewModel,
  private val editCategoryViewModel: EditCategoryViewModel,
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {
  fun onComposition() {
    feedLastbillCardViewModel.observe().baseSubscribe { }
  }

  override fun onCleared() {
    super.onCleared()
    editPlaceProductViewModel.onCleared()
    editCategoryViewModel.onCleared()
  }
}