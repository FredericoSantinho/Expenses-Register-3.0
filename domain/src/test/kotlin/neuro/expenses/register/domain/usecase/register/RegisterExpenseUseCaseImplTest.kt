package neuro.expenses.register.domain.usecase.register

import neuro.expenses.register.domain.di.useCaseModule
import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.entity.Bill
import neuro.expenses.register.domain.entity.BillItem
import neuro.expenses.register.domain.entity.Product
import neuro.expenses.register.domain.usecase.bill.GetLastBillUseCase
import neuro.expenses.register.domain.usecase.bill.SaveBillUseCase
import neuro.expenses.register.domain.usecase.register.validator.BillItemValidator
import neuro.expenses.register.domain.usecase.register.validator.RegisterExpenseError
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.mockito.ArgumentMatchers
import org.mockito.kotlin.*
import java.util.*
import kotlin.test.assertEquals

internal class RegisterExpenseUseCaseImplTest : KoinTest {
  @get:Rule
  val koinTestRule = KoinTestRule.create {
    modules(useCaseModule)
  }

  @Test
  fun openedBillExistingProduct() {
    val mockedBill = Bill(
      "place",
      1,
      listOf(BillItem(Product("desc", "", 0.0), 1.0))
    )
    val expectedBill = Bill(
      "place",
      1,
      listOf(BillItem(Product("desc", "", 0.0), 2.0))
    )

    val getLastBillUseCase = mock<GetLastBillUseCase> { getLastBill ->
      on { getLastBill.getLastBill() } doReturn Optional.of(
        mockedBill
      )
    }
    val billItemValidator =
      mock<BillItemValidator> { on { it.validate(any()) } doReturn emptyList() }
    val saveBillUseCase = mock<SaveBillUseCase> {}
    val registerExpenseUseCase =
      RegisterExpenseUseCaseImpl(getLastBillUseCase, saveBillUseCase, billItemValidator, get())


    val billItemDto = BillItemDto(ProductDto("desc", "", 0.0), "place", 1.0)
    val calendar = Calendar.getInstance()
    registerExpenseUseCase.registerExpense(billItemDto, calendar)

    verify(saveBillUseCase, times(1)).save(expectedBill, "place")
  }

  @Test
  fun openedBillNonExistingProduct() {
    val mockedBill = Bill(
      "place",
      1,
      listOf(BillItem(Product("desc1", "", 0.0), 1.0))
    )
    val expectedBill = Bill(
      "place",
      1,
      listOf(BillItem(Product("desc1", "", 0.0), 1.0), BillItem(Product("desc", "", 0.0), 1.0))
    )

    val getLastBillUseCase = mock<GetLastBillUseCase> { getLastBill ->
      on { getLastBill.getLastBill() } doReturn Optional.of(
        mockedBill
      )
    }
    val billItemValidator =
      mock<BillItemValidator> { on { it.validate(any()) } doReturn emptyList() }
    val saveBillUseCase = mock<SaveBillUseCase> {}
    val registerExpenseUseCase =
      RegisterExpenseUseCaseImpl(getLastBillUseCase, saveBillUseCase, billItemValidator, get())


    val billItemDto = BillItemDto(ProductDto("desc", "", 0.0), "place", 1.0)
    val calendar = Calendar.getInstance()
    registerExpenseUseCase.registerExpense(billItemDto, calendar)

    verify(saveBillUseCase, times(1)).save(expectedBill, "place")
  }

  @Test
  fun openedBillDifferentPlace() {
    val mockedBill = Bill(
      "place",
      1,
      listOf(BillItem(Product("desc", "", 0.0), 1.0))
    )
    val expectedBill = Bill(
      "place2",
      1,
      listOf(BillItem(Product("desc", "", 0.0), 1.0))
    )

    val getLastBillUseCase = mock<GetLastBillUseCase> { getLastBill ->
      on { getLastBill.getLastBill() } doReturn Optional.of(
        mockedBill
      )
    }
    val billItemValidator =
      mock<BillItemValidator> { on { it.validate(any()) } doReturn emptyList() }
    val saveBillUseCase = mock<SaveBillUseCase> {}
    val registerExpenseUseCase =
      RegisterExpenseUseCaseImpl(getLastBillUseCase, saveBillUseCase, billItemValidator, get())


    val billItemDto = BillItemDto(ProductDto("desc", "", 0.0), "place2", 1.0)
    val calendar = Calendar.getInstance()
    calendar.time = Date(1)
    registerExpenseUseCase.registerExpense(billItemDto, calendar)

    verify(saveBillUseCase, times(1)).save(expectedBill, "place2")
  }

  @Test
  fun closedBill() {
    val mockedBill = Bill(
      "place",
      1,
      listOf(BillItem(Product("desc", "", 0.0), 2.0)), false
    )
    val expectedBill = Bill(
      "place",
      1,
      listOf(BillItem(Product("desc", "", 0.0), 1.0))
    )

    val getLastBillUseCase = mock<GetLastBillUseCase> { getLastBill ->
      on { getLastBill.getLastBill() } doReturn Optional.of(
        mockedBill
      )
    }
    val billItemValidator =
      mock<BillItemValidator> { on { it.validate(any()) } doReturn emptyList() }
    val saveBillUseCase = mock<SaveBillUseCase> {}
    val registerExpenseUseCase =
      RegisterExpenseUseCaseImpl(getLastBillUseCase, saveBillUseCase, billItemValidator, get())


    val billItemDto = BillItemDto(ProductDto("desc", "", 0.0), "place", 1.0)
    val calendar = Calendar.getInstance()
    calendar.time = Date(1)
    registerExpenseUseCase.registerExpense(billItemDto, calendar)

    verify(saveBillUseCase, times(1)).save(expectedBill, "place")
  }

  @Test
  fun testValidatorUsage() {
    val mockedBill = Bill(
      "place",
      1,
      listOf(BillItem(Product("desc", "", 0.0), 2.0)), false
    )

    val getLastBillUseCase = mock<GetLastBillUseCase> { getLastBill ->
      on { getLastBill.getLastBill() } doReturn Optional.of(mockedBill)
    }
    val billItemValidator =
      mock<BillItemValidator> { on { it.validate(any()) } doReturn getErrorList() }
    val saveBillUseCase = mock<SaveBillUseCase> {}
    val registerExpenseUseCase =
      RegisterExpenseUseCaseImpl(getLastBillUseCase, saveBillUseCase, billItemValidator, get())


    val billItemDto = BillItemDto(ProductDto("desc", "", 0.0), "place", 1.0)
    val calendar = Calendar.getInstance()
    calendar.time = Date(1)

    assertEquals(getErrorList(), registerExpenseUseCase.registerExpense(billItemDto, calendar))

    verify(saveBillUseCase, times(0)).save(any(), ArgumentMatchers.anyString())
  }

  private fun getErrorList(): List<RegisterExpenseError> {
    return listOf(RegisterExpenseError.EMPTY_PLACE, RegisterExpenseError.INVALID_CATEGORY)
  }
}