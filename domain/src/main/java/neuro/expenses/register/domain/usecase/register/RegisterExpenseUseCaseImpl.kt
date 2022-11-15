package neuro.expenses.register.domain.usecase.register

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.mapper.BillMapper
import neuro.expenses.register.domain.mapper.ExpenseMapper
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.SaveBillUseCase
import neuro.expenses.register.domain.usecase.register.validator.ExpenseValidator
import neuro.expenses.register.entity.Bill
import neuro.expenses.register.entity.controller.BillController
import java.util.*

class RegisterExpenseUseCaseImpl(
  private val billController: BillController,
  private val getLastBillUseCase: GetLastBillUseCase,
  private val saveBillUseCase: SaveBillUseCase,
  private val expenseValidator: ExpenseValidator,
  private val billMapper: BillMapper,
  private val expenseMapper: ExpenseMapper
) : RegisterExpenseUseCase {
  private val defaultBillDto = BillDto(0, "N/A", Calendar.getInstance(), 0.0, isOpen = false)

  override fun registerExpense(
    expenseDto: ExpenseDto
  ): Completable {
    return getLastBillUseCase.getLastBill().defaultIfEmpty(defaultBillDto)
      .map { billMapper.map(it) }.flatMapCompletable { lastStoredBill ->
        val expense = expenseMapper.map(expenseDto)

        val place = expense.place
        val calendar = expense.calendar
        val lastBill = if (!lastStoredBill.isOpen || lastStoredBill.place != place) {
          Bill(0, place, calendar)
        } else {
          lastStoredBill
        }

        return@flatMapCompletable expenseValidator.validate(expense).andThen(Completable.defer {
          return@defer billController.add(lastBill, expense).doOnSuccess { bill ->
            saveBillUseCase.save(bill)
          }.ignoreElement()
        })
      }
  }
}