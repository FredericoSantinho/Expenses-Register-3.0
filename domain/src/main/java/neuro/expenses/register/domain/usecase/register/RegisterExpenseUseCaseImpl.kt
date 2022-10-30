package neuro.expenses.register.domain.usecase.register

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.entity.controller.BillController
import neuro.expenses.register.domain.mapper.BillItemDtoMapper
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.SaveBillUseCase
import neuro.expenses.register.domain.usecase.register.validator.BillItemValidator
import neuro.expenses.register.domain.usecase.register.validator.RegisterExpenseError

class RegisterExpenseUseCaseImpl(
  val getLastBillUseCase: GetLastBillUseCase,
  val saveBillUseCase: SaveBillUseCase,
  val billItemValidator: BillItemValidator,
  val billItemDtoMapper: BillItemDtoMapper
) : RegisterExpenseUseCase {
  override fun registerExpense(
    billItemDto: BillItemDto
  ): List<RegisterExpenseError> {
    val lastBillOptional = getLastBillUseCase.getLastBill()
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
      val lastBillController = BillController(lastBill)
      val billItem = billItemDtoMapper.map(billItemDto)

      lastBillController.add(billItem)
      saveBillUseCase.save(lastBillController.bill, place)
    }

    return validate
  }
}