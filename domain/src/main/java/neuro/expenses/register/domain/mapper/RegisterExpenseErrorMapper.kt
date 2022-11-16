package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError

fun neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError.toDomain(): RegisterExpenseError =
  when (this) {
    neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError.EMPTY_DESCRIPTION -> RegisterExpenseError.EMPTY_DESCRIPTION
    neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError.INVALID_CATEGORY -> RegisterExpenseError.INVALID_CATEGORY
    neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError.EMPTY_PLACE -> RegisterExpenseError.EMPTY_PLACE
    neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError.INVALID_AMOUNT -> RegisterExpenseError.INVALID_AMOUNT
  }

fun List<neuro.expenses.register.entity.controller.expense.validator.RegisterExpenseError>.toDomain(): List<RegisterExpenseError> =
  map { it.toDomain() }