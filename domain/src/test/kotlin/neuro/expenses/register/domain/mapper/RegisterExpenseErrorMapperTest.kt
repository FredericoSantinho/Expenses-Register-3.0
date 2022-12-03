package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.usecase.expense.RegisterExpenseError
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RegisterExpenseErrorMapperTest {
  @Test
  fun test() {
    assertEquals(
      neuro.expenses.register.entity.expense.validator.RegisterExpenseError.EMPTY_DESCRIPTION.toDomain(),
      RegisterExpenseError.EMPTY_DESCRIPTION
    )
    assertEquals(
      neuro.expenses.register.entity.expense.validator.RegisterExpenseError.CATEGORY_NOT_EXISTS.toDomain(),
      RegisterExpenseError.CATEGORY_NOT_EXISTS
    )
    assertEquals(
      neuro.expenses.register.entity.expense.validator.RegisterExpenseError.EMPTY_PLACE.toDomain(),
      RegisterExpenseError.EMPTY_PLACE
    )
    assertEquals(
      neuro.expenses.register.entity.expense.validator.RegisterExpenseError.INVALID_AMOUNT.toDomain(),
      RegisterExpenseError.INVALID_AMOUNT
    )
  }
}