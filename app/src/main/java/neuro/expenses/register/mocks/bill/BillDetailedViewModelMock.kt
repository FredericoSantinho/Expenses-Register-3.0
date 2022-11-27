package neuro.expenses.register.mocks.bill

import neuro.expenses.register.viewmodel.bill.BillDetailedViewModel
import neuro.expenses.register.viewmodel.bill.mapper.BillItemModelMapper
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatter
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatterImpl
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatterImpl

class BillDetailedViewModelMock(
  private val decimalFormatter: DecimalFormatter = DecimalFormatterImpl(2),
  private val currencyFormatter: CurrencyFormatter = CurrencyFormatterImpl(decimalFormatter, "€"),
  private val billItemModelMapper: BillItemModelMapper = BillItemModelMapper(decimalFormatter)
) {
  fun createBillDetailedViewModel(): BillDetailedViewModel {
    val billDetailedViewModel = BillDetailedViewModel(billItemModelMapper, currencyFormatter)
    billDetailedViewModel.placeName.value = "Bitoque"
    billDetailedViewModel.billItems.value = emptyList()
    return billDetailedViewModel
  }
}