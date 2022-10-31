package neuro.expenses.register.domain.usecase.register

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import neuro.expenses.register.domain.controller.BillController
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.entity.controller.CalculateBillTotal
import neuro.expenses.register.domain.mapper.BillMapper
import neuro.expenses.register.domain.mapper.ExpenseMapper
import neuro.expenses.register.domain.usecase.bill.ObserveLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.SaveBillUseCase
import neuro.expenses.register.domain.usecase.product.GetOrCreateProductUseCase
import neuro.expenses.register.domain.usecase.register.validator.ExpenseValidator
import neuro.expenses.register.domain.usecase.register.validator.RegisterExpenseError
import java.util.*

class RegisterExpenseUseCaseImpl(
  private val observeLastBillUseCase: ObserveLastBillUseCase,
  private val saveBillUseCase: SaveBillUseCase,
  private val getOrCreateProductUseCase: GetOrCreateProductUseCase,
  private val expenseValidator: ExpenseValidator,
  private val billMapper: BillMapper,
  private val expenseMapper: ExpenseMapper,
  private val calculateBillTotal: CalculateBillTotal
) : RegisterExpenseUseCase {
  override fun registerExpense(
    expenseDto: ExpenseDto
  ): Single<List<RegisterExpenseError>> {
    return observeLastBillUseCase.observeLastBill().singleOrError().subscribeOn(Schedulers.io())
      .map { if (it.isPresent) Optional.of(billMapper.map(it.get())) else Optional.empty<Bill>() }
      .flatMap { lastBillOptional ->
        val place = expenseDto.place
        val calendar = expenseDto.calendar
        val lastBill =
          if (!lastBillOptional.isPresent || !lastBillOptional.get().isOpen || lastBillOptional.get().place != place) {
            Bill(place, calendar.timeInMillis)
          } else {
            lastBillOptional.get()
          }

        val expense = expenseMapper.map(expenseDto)
        val validate = expenseValidator.validate(expense)

        if (validate.isEmpty()) {
          val lastBillController =
            BillController(calculateBillTotal, getOrCreateProductUseCase, lastBill)
          val billItem = expenseMapper.map(expenseDto)

          return@flatMap lastBillController.add(billItem).doOnComplete {
            saveBillUseCase.save(
              lastBillController.bill,
              place
            )
          }.toSingleDefault(validate)
        }

        return@flatMap Single.just(validate)
      }
  }
}