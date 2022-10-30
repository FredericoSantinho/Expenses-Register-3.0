package neuro.expenses.register.domain.usecase.bill

import neuro.expenses.register.domain.entity.Bill
import java.util.*

class GetLastBillUseCaseImpl : GetLastBillUseCase {
  override fun getLastBill(): Optional<Bill> {
    return Optional.empty()
  }
}