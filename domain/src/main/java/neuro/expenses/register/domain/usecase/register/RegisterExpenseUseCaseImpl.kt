package neuro.expenses.register.domain.usecase.register

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.entity.controller.BillController
import neuro.expenses.register.domain.entity.controller.CalculateBillTotal
import neuro.expenses.register.domain.mapper.BillDtoMapper
import neuro.expenses.register.domain.mapper.BillItemDtoMapper
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.SaveBillUseCase
import neuro.expenses.register.domain.usecase.register.validator.BillItemValidator
import neuro.expenses.register.domain.usecase.register.validator.RegisterExpenseError
import java.util.*

class RegisterExpenseUseCaseImpl(
  private val getLastBillUseCase: GetLastBillUseCase,
  private val saveBillUseCase: SaveBillUseCase,
  private val billItemValidator: BillItemValidator,
  private val billItemDtoMapper: BillItemDtoMapper,
  private val billDtoMapper: BillDtoMapper,
  private val calculateBillTotal: CalculateBillTotal
) : RegisterExpenseUseCase {
  override fun registerExpense(
    billItemDto: BillItemDto
  ): Single<List<RegisterExpenseError>> {
    return getLastBillUseCase.getLastBill().singleOrError().subscribeOn(Schedulers.io())
      .map { if (it.isPresent) Optional.of(billDtoMapper.map(it.get())) else Optional.empty<Bill>() }
      .map { lastBillOptional ->
        val place = billItemDto.place
        val calendar = billItemDto.calendar
        val lastBill =
          if (!lastBillOptional.isPresent || !lastBillOptional.get().isOpen || lastBillOptional.get().place != place) {
            Bill(place, calendar.timeInMillis)
          } else {
            lastBillOptional.get()
          }

        val validate = billItemValidator.validate(billItemDto)

        if (validate.isEmpty()) {
          val lastBillController = BillController(calculateBillTotal, lastBill)
          val billItem = billItemDtoMapper.map(billItemDto)

          lastBillController.add(billItem)
          saveBillUseCase.save(lastBillController.bill, place)
        }

        return@map validate
      }
  }
}