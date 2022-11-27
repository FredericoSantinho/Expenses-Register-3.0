package neuro.expenses.register.viewmodel.bills

import neuro.expenses.register.domain.usecase.bill.GetBillUseCase
import neuro.expenses.register.domain.usecase.bill.ObserveBillsUseCase
import neuro.expenses.register.domain.usecase.bill.SortBills
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.bill.BillDetailedViewModel
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.mapper.BillViewModelMapper
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState

class BillsViewModel(
  private val observeBillsUseCase: ObserveBillsUseCase,
  private val getBillUseCase: GetBillUseCase,
  private val sortBills: SortBills,
  private val billViewModelMapper: BillViewModelMapper,
  private val billDetailedViewModel: BillDetailedViewModel,
  private val scaffoldViewModelState: ScaffoldViewModelState,
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

  private val _uiEvent = BillsUiEvent()
  val uiEvent = _uiEvent.uiEvent

  init {
    // TODO: Move to right place
    appBarViewModel.enableSearch()
  }

  fun onBillSwipe(item: BillViewModel) {
    // TODO: remove bill
  }

  fun onComposition() {
    scaffoldViewModelState.reset()
    scaffoldViewModelState.appBarViewModel.value = appBarViewModel
  }

  fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  private fun onBillLongClick(billId: Long) {
    getBillUseCase.getBill(billId).baseSubscribe { billDto ->
      billDetailedViewModel.setBillDetailedViewModel(billDto)
      _uiEvent.openBillDetailed()
    }
  }
}
