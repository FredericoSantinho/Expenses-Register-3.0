package neuro.expenses.register.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class ExpenseTest {
  @Test
  fun test() {
    val description = "desc"
    val category = "cat"
    val place = "place"
    val price = 1.0
    val amount = 2.0
    val calendar = Calendar.getInstance()

    val expense = Expense(description, category, place, price, amount, calendar)
    val expenseEqual = Expense(description, category, place, price, amount, calendar)
    val expenseDifferent = Expense(description + "diff", category, place, price, amount, calendar)

    assertEquals(expense.description, description)
    assertEquals(expense.category, category)
    assertEquals(expense.place, place)
    assertEquals(expense.price, price)
    assertEquals(expense.amount, amount)
    assertEquals(expense.calendar, calendar)

    assertEquals(expense, expenseEqual)
    assertNotEquals(expense, expenseDifferent)
  }

}