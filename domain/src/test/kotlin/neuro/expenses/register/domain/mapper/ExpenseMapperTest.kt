package neuro.expenses.register.domain.mapper

import neuro.expenses.register.entity.mocks.expenseDtoMock
import neuro.expenses.register.entity.test.mocks.expenseMock
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExpenseMapperTest {
  @Test
  fun test() {
    val expense = expenseMock(2)
    val expenseDto = expenseDtoMock(2)

    assertEquals(expenseDto, expense.toDomain())
    assertEquals(expense, expenseDto.toEntity())
  }
}