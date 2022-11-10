package neuro.expenses.register.ui.bills

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.ui.common.bill.BillViewModel

class BillsViewModel : ViewModel() {
  val bills = mutableStateListOf<BillViewModel>()
  val isEditMode = mutableStateOf(false)

  init {
    bills.addAll(getList())
  }

  private fun getList(): List<BillViewModel> {
    val list = mutableListOf<BillViewModel>()
    for (i in 1..20) {
      val billViewModel = BillViewModel()
      billViewModel.setEditableBillState()
      list.add(billViewModel)
    }
    return list
  }

  fun onBillSwipe(item: BillViewModel) {
    bills.remove(item)
  }
}