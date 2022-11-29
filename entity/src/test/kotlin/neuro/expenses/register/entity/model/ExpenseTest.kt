package neuro.expenses.register.entity.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

internal class ExpenseTest {
  @Test
  fun test() {
    val description = "description"
    val category = "cat"
    val place = "place"
    val price = 1.0
    val amount = 2.0
    val calendar = Calendar.getInstance()

    val expense = Expense(description, category, place, price, amount, calendar)
    val expenseEqual = Expense(description, category, place, price, amount, calendar)
    val expenseDifferent = Expense(description + "diff", category, place, price, amount, calendar)

    Assertions.assertEquals(expense.description, description)
    Assertions.assertEquals(expense.category, category)
    Assertions.assertEquals(expense.place, place)
    Assertions.assertEquals(expense.price, price)
    Assertions.assertEquals(expense.amount, amount)
    Assertions.assertEquals(expense.calendar, calendar)

    Assertions.assertEquals(expense, expenseEqual)
    Assertions.assertNotEquals(expense, expenseDifferent)
  }

}