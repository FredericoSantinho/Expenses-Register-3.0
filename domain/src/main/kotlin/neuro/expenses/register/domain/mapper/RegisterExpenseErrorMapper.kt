package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError

fun neuro.expenses.register.entity.expense.validator.RegisterExpenseError.toDomain(): RegisterExpenseError =
  when (this) {
    neuro.expenses.register.entity.expense.validator.RegisterExpenseError.EMPTY_DESCRIPTION -> RegisterExpenseError.EMPTY_DESCRIPTION
    neuro.expenses.register.entity.expense.validator.RegisterExpenseError.INVALID_CATEGORY -> RegisterExpenseError.CATEGORY_NOT_EXISTS
    neuro.expenses.register.entity.expense.validator.RegisterExpenseError.EMPTY_PLACE -> RegisterExpenseError.EMPTY_PLACE
    neuro.expenses.register.entity.expense.validator.RegisterExpenseError.INVALID_AMOUNT -> RegisterExpenseError.INVALID_AMOUNT
  }

fun List<neuro.expenses.register.entity.expense.validator.RegisterExpenseError>.toDomain(): List<RegisterExpenseError> =
  map { it.toDomain() }