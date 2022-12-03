package neuro.expenses.register.domain.usecase.expense

import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RegisterExpenseExceptionTest {
  @Test
  fun test() {
    val registerExpenseException = newRegisterExpenseException()
    assertEquals(registerExpenseException, registerExpenseException)
    assertEquals(newRegisterExpenseException(), newRegisterExpenseException())
    assertNotEquals(newRegisterExpenseException(), IllegalStateException())
    assertNotEquals(newRegisterExpenseException(), null)
    assertNotEquals(newRegisterExpenseException(), newDifferentRegisterExpenseException())
    assertEquals(newRegisterExpenseException().errors, newRegisterExpenseErrors())

    val map = mutableMapOf<Any, Boolean>()
    map.put(newRegisterExpenseException(), true)

    assertEquals(1, map.values.size)

    map.put(newRegisterExpenseException(), true)

    assertEquals(1, map.values.size)
  }

  private fun newDifferentRegisterExpenseException(): RegisterExpenseException {
    return RegisterExpenseException(listOf(RegisterExpenseError.EMPTY_DESCRIPTION))
  }

  private fun newRegisterExpenseException(): RegisterExpenseException {
    return RegisterExpenseException(newRegisterExpenseErrors())
  }

  private fun newRegisterExpenseErrors() =
    listOf(RegisterExpenseError.EMPTY_DESCRIPTION, RegisterExpenseError.EMPTY_PLACE)
}