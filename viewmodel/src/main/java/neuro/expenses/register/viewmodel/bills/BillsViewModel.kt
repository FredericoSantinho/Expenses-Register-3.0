package neuro.expenses.register.viewmodel.bills

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.domain.usecase.bill.ObserveBillsUseCase
import neuro.expenses.register.domain.usecase.bill.SortBills
import neuro.expenses.register.viewmodel.appbar.AppBarViewModel
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.mapper.BillViewModelMapper
import neuro.expenses.register.viewmodel.bill.model.BillModel
import neuro.expenses.register.viewmodel.common.asState
import neuro.expenses.register.viewmodel.edit.bill.EditBillViewModel
import neuro.expenses.register.viewmodel.home.UiEvent
import neuro.expenses.register.viewmodel.main.MainViewModel

class BillsViewModel(
  private val observeBillsUseCase: ObserveBillsUseCase,
  private val sortBills: SortBills,
  private val billViewModelMapper: BillViewModelMapper,
  private val editBillViewModel: EditBillViewModel,
  private val mainViewModel: MainViewModel
) : ViewModel() {
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

  private fun onBillLongClick(billModel: BillModel) {
    setEditBillViewModel(billModel)
    _uiEvent.value = UiEvent.OpenEditMode()
  }

  private fun setEditBillViewModel(billModel: BillModel) {
    editBillViewModel.placeName.value = billModel.place
    editBillViewModel.calendar.value = billModel.calendar
  }
}