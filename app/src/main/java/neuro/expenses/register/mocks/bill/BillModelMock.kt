package neuro.expenses.register.mocks.bill

import neuro.expenses.register.viewmodel.bill.model.BillModel
import java.util.*

fun billModelMock(): BillModel {
  return BillModel(
    0,
    "",
    "Bitoque",
    "10h36",
    "24/11/2022",
    "3.60 €",
    true,
    Calendar.getInstance()
  )
}
