package neuro.expenses.register.mocks.bill

import neuro.expenses.register.viewmodel.bill.BillDetailedViewModel
import neuro.expenses.register.viewmodel.bill.mapper.BillItemModelMapper
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatterImpl
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatterImpl

fun billDetailedViewModelMock(): BillDetailedViewModel {
  val decimalFormatter = DecimalFormatterImpl(2)
  val currencyFormatter = CurrencyFormatterImpl(decimalFormatter, "€")
  val billItemModelMapper = BillItemModelMapper(decimalFormatter)

  val billDetailedViewModel = BillDetailedViewModel(billItemModelMapper, currencyFormatter)
  billDetailedViewModel.placeName.value = "Bitoque"
  billDetailedViewModel.billItems.value = emptyList()
  return billDetailedViewModel
}
