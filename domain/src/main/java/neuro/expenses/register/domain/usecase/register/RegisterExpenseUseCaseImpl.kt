package neuro.expenses.register.domain.usecase.register

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.controller.BillController
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.entity.controller.CalculateBillTotal
import neuro.expenses.register.domain.mapper.BillMapper
import neuro.expenses.register.domain.mapper.ExpenseMapper
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.ObserveLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.SaveBillUseCase
import neuro.expenses.register.domain.usecase.product.GetOrCreateProductUseCase
import neuro.expenses.register.domain.usecase.register.validator.ExpenseValidator
import neuro.expenses.register.domain.usecase.register.validator.RegisterExpenseError

class RegisterExpenseUseCaseImpl(
  private val observeLastBillUseCase: ObserveLastBillUseCase,
  private val getLastBillUseCase: GetLastBillUseCase,
  private val saveBillUseCase: SaveBillUseCase,
  private val getOrCreateProductUseCase: GetOrCreateProductUseCase,
  private val expenseValidator: ExpenseValidator,
  private val billMapper: BillMapper,
  private val expenseMapper: ExpenseMapper,
  private val calculateBillTotal: CalculateBillTotal
) : RegisterExpenseUseCase {
  private val defaultBillDto = BillDto(0, "N/A", 0, 1.0)

  override fun registerExpense(
    expenseDto: ExpenseDto
  ): Single<List<RegisterExpenseError>> {
    return getLastBillUseCase.getLastBill().defaultIfEmpty(defaultBillDto)
      .map { billMapper.map(it) }.flatMap { lastStoredBill ->
        val place = expenseDto.place
        val calendar = expenseDto.calendar
        val lastBill = if (!lastStoredBill.isOpen || lastStoredBill.place != place) {
          Bill(0, place, calendar.timeInMillis)
        } else {
          lastStoredBill
        }

        val expense = expenseMapper.map(expenseDto)

        expenseValidator.validate(expense).flatMap innerFlatMap@{ validate ->
          if (validate.isEmpty()) {
            val lastBillController =
              BillController(calculateBillTotal, getOrCreateProductUseCase, lastBill)
            val billItem = expenseMapper.map(expenseDto)

            return@innerFlatMap lastBillController.add(billItem).doOnComplete {
              saveBillUseCase.save(
                lastBillController.bill
              )
            }.toSingleDefault(validate)
          }

          return@innerFlatMap Single.just(validate)
        }
      }
  }
}