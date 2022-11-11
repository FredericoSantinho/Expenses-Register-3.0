package neuro.expenses.register.domain.usecase.register.validator

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.usecase.category.IsValidCategoryUseCase
import neuro.expenses.register.entity.Expense

class ExpenseValidatorImpl(val isValidCategoryUseCase: IsValidCategoryUseCase) : ExpenseValidator {
  override fun validate(expense: Expense): Completable {
    return Single.fromCallable { mutableListOf<RegisterExpenseError>() }
      .flatMapCompletable { errors ->
        if (expense.description.isBlank()) {
          errors.add(RegisterExpenseError.EMPTY_DESCRIPTION)
        }
        if (expense.place.isBlank()) {
          errors.add(RegisterExpenseError.EMPTY_PLACE)
        }
        if (expense.amount <= 0) {
          errors.add(RegisterExpenseError.INVALID_AMOUNT)
        }
        isValidCategoryUseCase.isValidCategory(expense.category).doOnSuccess { isValidCategory ->
          if (!isValidCategory) {
            errors.add(RegisterExpenseError.INVALID_CATEGORY)
          }
        }.filter { errors.isNotEmpty() }
          .flatMapCompletable { Completable.error(RegisterExpenseException(errors)) }
      }
  }
}