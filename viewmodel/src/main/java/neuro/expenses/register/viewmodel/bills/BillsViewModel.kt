package neuro.expenses.register.viewmodel.bills

import neuro.expenses.register.domain.usecase.bill.GetBillUseCase
import neuro.expenses.register.domain.usecase.bill.ObserveBillsUseCase
import neuro.expenses.register.domain.usecase.bill.SortBills
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.appbar.Title
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

  fun onBillSwipe(item: BillViewModel) {
    // TODO: remove bill
  }

  fun onComposition() {
    setupScaffold()
  }

  fun eventConsumed() {
    _uiEvent.eventConsumed()
  }

  private fun setupScaffold() {
    appBarViewModel.title.value = BillsTitle
    appBarViewModel.enableSearch()
    scaffoldViewModelState.reset()
    scaffoldViewModelState.appBarViewModel.value = appBarViewModel
  }

  private fun onBillLongClick(billId: Long) {
    getBillUseCase.getBill(billId).baseSubscribe { billDto ->
      billDetailedViewModel.setBillDetailedViewModel(billDto)
      _uiEvent.openBillDetailed()
    }
  }
}

object BillsTitle : Title()