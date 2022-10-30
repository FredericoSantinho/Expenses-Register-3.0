package neuro.expenses.register.domain.usecase.bill

import neuro.expenses.register.domain.entity.Bill
import java.util.*

interface GetLastBillUseCase {
  fun getLastBill(): Optional<Bill>
}