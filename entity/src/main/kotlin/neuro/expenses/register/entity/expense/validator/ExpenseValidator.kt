package neuro.expenses.register.entity.expense.validator

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.entity.model.Expense

interface ExpenseValidator {
  /**
   * Validate an Expense with the following rules:
   *
   * - Non empty description.
   * - Valid category (ie, existent).
   * - Non empty place.
   * - Positive amount.
   *
   * @param Expense to validate.
   * @return Completable that completes if validation succeeds, or emmits an error with a
   * RegisterExpenseException containing a list of RegisterExpenseError.
   */
  fun validate(expense: Expense): Completable
}