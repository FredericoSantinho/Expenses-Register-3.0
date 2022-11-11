package neuro.expenses.register.domain.usecase.register

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.controller.BillController
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.mapper.BillMapper
import neuro.expenses.register.domain.mapper.ExpenseMapper
import neuro.expenses.register.domain.mapper.ProductMapper
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.SaveBillUseCase
import neuro.expenses.register.domain.usecase.register.validator.ExpenseValidator
import neuro.expenses.register.entity.Bill
import neuro.expenses.register.entity.controller.CalculateBillTotal
import java.util.*

class RegisterExpenseUseCaseImpl(
  private val getLastBillUseCase: GetLastBillUseCase,
  private val saveBillUseCase: SaveBillUseCase,
  private val expenseValidator: ExpenseValidator,
  private val billMapper: BillMapper,
  private val productMapper: ProductMapper,
  private val expenseMapper: ExpenseMapper,
  private val calculateBillTotal: CalculateBillTotal
) : RegisterExpenseUseCase {
  private val defaultBillDto = BillDto(0, "N/A", Calendar.getInstance(), 0.0, isOpen = false)

  override fun registerExpense(
    expenseDto: ExpenseDto
  ): Completable {
    return getLastBillUseCase.getLastBill().defaultIfEmpty(defaultBillDto)
      .map { billMapper.map(it) }.flatMapCompletable { lastStoredBill ->
        val place = expenseDto.place
        val calendar = expenseDto.calendar
        val lastBill = if (!lastStoredBill.isOpen || lastStoredBill.place != place) {
          Bill(0, place, calendar)
        } else {
          lastStoredBill
        }

        val expense = expenseMapper.map(expenseDto)

        return@flatMapCompletable expenseValidator.validate(expense).andThen(Completable.defer {
          val lastBillController =
            BillController(calculateBillTotal, productMapper, lastBill)

          return@defer lastBillController.add(expense).doOnComplete {
            saveBillUseCase.save(
              lastBillController.bill
            )
          }
        })
      }
  }
}