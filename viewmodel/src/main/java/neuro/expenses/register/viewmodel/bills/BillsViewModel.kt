package neuro.expenses.register.viewmodel.bills

import androidx.compose.runtime.mutableStateOf
import neuro.expenses.register.domain.usecase.bill.GetBillUseCase
import neuro.expenses.register.domain.usecase.bill.ObserveBillsUseCase
import neuro.expenses.register.domain.usecase.bill.SortBills
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.appbar.Title
import neuro.expenses.register.viewmodel.bill.BillDetailedViewModel
import neuro.expenses.register.viewmodel.bill.IBillCardViewModel
import neuro.expenses.register.viewmodel.bill.mapper.billCardViewModelMapper
import neuro.expenses.register.viewmodel.common.BaseViewModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider
import neuro.expenses.register.viewmodel.scaffold.ScaffoldViewModelState

class BillsViewModel(
  private val observeBillsUseCase: ObserveBillsUseCase,
  private val getBillUseCase: GetBillUseCase,
  private val sortBills: SortBills,
  private val billCardViewModelMapper: billCardViewModelMapper,
  private val billDetailedViewModel: BillDetailedViewModel,
  private val scaffoldViewModelState: ScaffoldViewModelState,
  schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {
  val appBarViewModel: AppBarViewModel = AppBarViewModel()

  val bills = mutableStateOf<List<IBillCardViewModel>>(emptyList())

  private val _uiEvent = BillsUiEvent()
  val uiEvent = _uiEvent.uiEvent

  fun onBillSwipe(item: IBillCardViewModel) {
    // TODO: remove bill
  }

  fun onComposition() {
    setupScaffold()
    observeBills()
  }

  private fun observeBills() {
    observeBillsUseCase.observeBills()
      .map { sortBills.sortBills(it) }
      .map { billDtos ->
        billDtos.map { billDto ->
          billCardViewModelMapper.map(billDto, false, false, ::onBillLongClick)
        }
      }.baseSubscribe({
        bills.value = it
      }, {
        throw it
      })
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