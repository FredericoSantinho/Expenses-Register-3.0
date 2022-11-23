package neuro.expenses.register.viewmodel.bills

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.usecase.bill.GetBillUseCase
import neuro.expenses.register.domain.usecase.bill.ObserveBillsUseCase
import neuro.expenses.register.domain.usecase.bill.SortBills
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.mapper.BillViewModelMapper
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.main.MainViewModel

class BillsViewModel(
  private val observeBillsUseCase: ObserveBillsUseCase,
  private val getBillUseCase: GetBillUseCase,
  private val sortBills: SortBills,
  private val billViewModelMapper: BillViewModelMapper,
  private val editBillViewModelController: EditBillViewModelController,
  private val mainViewModel: MainViewModel,
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {
  val appBarViewModel: AppBarViewModel = AppBarViewModel()

  val bills = observeBillsUseCase.observeBills()
    .map { sortBills.sortBills(it) }
    .map { billDtos ->
      billDtos.map { billDto ->
        billViewModelMapper.map(billDto, false, false, ::onBillLongClick)
      }
    }

  private val _uiEvent = mutableStateOf<UiEvent?>(null)
  val uiEvent = _uiEvent.asState()

  init {
    // TODO: Move to right place
    appBarViewModel.enableSearch()
  }

  fun onBillSwipe(item: BillViewModel) {
    // TODO: remove bill
  }

  fun onComposition() {
    mainViewModel.appBarViewModelState.value = appBarViewModel
  }

  fun eventConsumed() {
    _uiEvent.value = null
  }

  private fun onBillLongClick(billId: Long) {
    getBillUseCase.getBill(billId).baseSubscribe { billDto ->
      editBillViewModelController.setEditBillViewModel(billDto)
      _uiEvent.value = UiEvent.OpenEditMode()
    }
  }
}

sealed class UiEvent {
  class OpenEditMode : UiEvent()
  class CloseEditMode : UiEvent()
}