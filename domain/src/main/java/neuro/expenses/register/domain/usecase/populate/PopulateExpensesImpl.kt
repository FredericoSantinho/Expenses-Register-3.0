package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.usecase.expense.RegisterExpenseUseCase
import java.util.*

class PopulateExpensesImpl(private val registerExpenseUseCase: RegisterExpenseUseCase) :
  PopulateExpenses {
  override fun populateExpenses(): Completable {
    return Observable.fromIterable(generateFakeExpenses())
      .flatMapCompletable { registerExpenseUseCase.registerExpense(it) }
  }

  private fun generateFakeExpenses(): MutableList<ExpenseDto> {
    val random = Random()
    val fakeExpensesList = buildFakeExpensesList()
    val list = mutableListOf<ExpenseDto>()

    for (i in 1..100) {
      list.add(copyWithRandomCalendar(fakeExpensesList.get(random.nextInt(fakeExpensesList.size))))
    }

    return list
  }

  private fun copyWithRandomCalendar(expenseDto: ExpenseDto): ExpenseDto {
    val random = Random()

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
    calendar.set(Calendar.MONTH, random.nextInt(12))
    calendar.set(Calendar.DAY_OF_MONTH, random.nextInt(29))
    calendar.set(Calendar.HOUR_OF_DAY, random.nextInt(16) + 8)
    calendar.set(Calendar.MINUTE, random.nextInt(60))
    calendar.set(Calendar.SECOND, random.nextInt(60))

    return copyExpense(expenseDto, calendar)
  }

  private fun copyExpense(expenseDto: ExpenseDto, calendar: Calendar): ExpenseDto {
    return ExpenseDto(
      expenseDto.description,
      expenseDto.category,
      expenseDto.place,
      expenseDto.price,
      expenseDto.amount,
      calendar
    )
  }

  private fun buildFakeExpensesList(): List<ExpenseDto> {
    val list = mutableListOf<ExpenseDto>()
    list.add(
      ExpenseDto(
        sagresMediaDescription,
        borgaName,
        vizinhaName,
        price11,
        1.0,
        Calendar.getInstance()
      )
    )
    list.add(
      ExpenseDto(
        sagresMediaDescription,
        borgaName,
        bitoqueName,
        price13,
        1.0,
        Calendar.getInstance()
      )
    )
    list.add(
      ExpenseDto(
        superBockMediaDescription,
        borgaName,
        bitoqueName,
        price13,
        1.0,
        Calendar.getInstance()
      )
    )
    list.add(
      ExpenseDto(
        miniCristalDescription,
        borgaName,
        bitoqueName,
        price11,
        1.0,
        Calendar.getInstance()
      )
    )
    list.add(
      ExpenseDto(
        miniSagresDescription,
        borgaName,
        bitoqueName,
        price11,
        1.0,
        Calendar.getInstance()
      )
    )
    list.add(
      ExpenseDto(
        chicharricosDescription,
        restauName,
        bitoqueName,
        price15,
        1.0,
        Calendar.getInstance()
      )
    )
    list.add(
      ExpenseDto(
        twixDescription,
        restauName,
        bitoqueName,
        price15,
        1.0,
        Calendar.getInstance()
      )
    )
    list.add(
      ExpenseDto(
        tostaMistaCaseiraDescription,
        restauName,
        bitoqueName,
        price30,
        1.0,
        Calendar.getInstance()
      )
    )
    list.add(
      ExpenseDto(
        sagresMediaDescription,
        restauName,
        bitoqueName,
        price11,
        1.0,
        Calendar.getInstance()
      )
    )
    list.add(
      ExpenseDto(
        caneca50Description,
        borgaName,
        longoBarName,
        price25,
        1.0,
        Calendar.getInstance()
      )
    )
    return list
  }
}