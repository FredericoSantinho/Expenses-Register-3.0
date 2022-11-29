package neuro.expenses.register.domain.entity.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.entity.bill.GetLastBill
import neuro.expenses.register.entity.model.Bill

class GetLastBillImpl(private val getLastBillUseCase: GetLastBillUseCase) : GetLastBill {
  override fun getLastBill(): Maybe<Bill> {
    return getLastBillUseCase.getLastBill().map(BillDto::toEntity)
  }
}