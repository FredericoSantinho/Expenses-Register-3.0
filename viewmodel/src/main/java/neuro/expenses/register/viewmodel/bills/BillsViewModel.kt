package neuro.expenses.register.viewmodel.bills

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.domain.usecase.bill.ObserveBillsUseCase
import neuro.expenses.register.viewmodel.bill.BillViewModel
import neuro.expenses.register.viewmodel.bill.mapper.BillViewModelMapper

class BillsViewModel(
  private val observeBillsUseCase: ObserveBillsUseCase,
  private val billViewModelMapper: BillViewModelMapper
) : ViewModel() {
  val bills = observeBillsUseCase.observeBills().map { billDtos ->
    billDtos.map { billDto ->
      val billViewModel = billViewModelMapper.map(billDto, false)
      billViewModel.setClosedBillState()
      billViewModel
    }
  }
  val isEditMode = mutableStateOf(false)

  fun onBillSwipe(item: BillViewModel) {
    // TODO: remove bill
  }
}