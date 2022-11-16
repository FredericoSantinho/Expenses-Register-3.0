package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.domain.mapper.toEntity
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.entity.Bill
import neuro.expenses.register.entity.controller.bill.GetLastBill

class GetLastBillImpl(private val getLastBillUseCase: GetLastBillUseCase) : GetLastBill {
  override fun getLastBill(): Maybe<Bill> {
    return getLastBillUseCase.getLastBill().map { billDto -> billDto.toEntity() }
  }
}