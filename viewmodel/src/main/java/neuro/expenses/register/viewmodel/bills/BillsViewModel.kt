package neuro.expenses.register.viewmodel.bills

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.domain.usecase.bill.ObserveBillsUseCase
import neuro.expenses.register.domain.usecase.bill.SortBills
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.mapper.BillViewModelMapper
import neuro.expenses.register.viewmodel.main.MainViewModel

class BillsViewModel(
  private val observeBillsUseCase: ObserveBillsUseCase,
  private val sortBills: SortBills,
  private val billViewModelMapper: BillViewModelMapper,
  private val mainViewModel: MainViewModel
) : ViewModel() {
  val appBarViewModel: AppBarViewModel = AppBarViewModel()

  val bills = observeBillsUseCase.observeBills()
    .map { sortBills.sortBills(it) }
    .map { billDtos ->
      billDtos.map { billDto ->
        val billViewModel = billViewModelMapper.map(billDto, false)
        billViewModel.setClosedBillState()
        billViewModel
      }
    }
  val isEditMode = mutableStateOf(false)

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
}