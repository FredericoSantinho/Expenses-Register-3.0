package neuro.expenses.register.domain.usecase.expense

internal class RegisterExpenseUseCaseImplTest {
  //  @get:Rule
//  val koinTestRule = KoinTestRule.create {
//    modules(useCaseModule)
//  }
//
//  @Test
//  fun openedBillExistingProduct() {
//    val mockedBill = BillDto(
//      "place",
//      1,
//      0.0,
//      listOf(BillItemDto(ProductDto("desc", "", 0.0), "place", 1.0))
//    )
//    val expectedBill = Bill(
//      "place",
//      1,
//      0.0,
//      listOf(BillItem(Product("desc", "", 0.0), 2.0))
//    )
//
//    val getLastBillUseCase = mock<GetLastBillUseCase> { getLastBill ->
//      on { getLastBill.getLastBill() } doReturn Observable.just(Optional.of(
//        mockedBill
//      ))
//    }
//    val billItemValidator =
//      mock<BillItemValidator> { on { it.validate(any()) } doReturn emptyList() }
//    val saveBillUseCase = mock<SaveBillUseCase> {}
//    val registerExpenseUseCase =
//      RegisterExpenseUseCaseImpl(getLastBillUseCase, saveBillUseCase, billItemValidator, get(),)
//
//
//    val calendar = Calendar.getInstance()
//    val billItemDto = BillItemDto(ProductDto("desc", "", 0.0), "place", 1.0, calendar)
//    registerExpenseUseCase.registerExpense(billItemDto)
//
//    verify(saveBillUseCase, times(1)).save(expectedBill, "place")
//  }
//
//  @Test
//  fun openedBillNonExistingProduct() {
//    val mockedBill = Bill(
//      1,
//      listOf(BillItem(Product("desc1", "", 0.0), 1.0)),,
//      iconUrl = "place"
//    )
//    val expectedBill = Bill(
//      1,
//      listOf(BillItem(Product("desc1", "", 0.0), 1.0), BillItem(Product("desc", "", 0.0), 1.0)),,
//      iconUrl = "place"
//    )
//
//    val getLastBillUseCase = mock<GetLastBillUseCase> { getLastBill ->
//      on { getLastBill.getLastBill() } doReturn Optional.of(
//        mockedBill
//      )
//    }
//    val billItemValidator =
//      mock<BillItemValidator> { on { it.validate(any()) } doReturn emptyList() }
//    val saveBillUseCase = mock<SaveBillUseCase> {}
//    val registerExpenseUseCase =
//      RegisterExpenseUseCaseImpl(getLastBillUseCase, saveBillUseCase, billItemValidator, get(),)
//
//
//    val calendar = Calendar.getInstance()
//    val billItemDto = BillItemDto(ProductDto("desc", "", 0.0), "place", 1.0, calendar)
//    registerExpenseUseCase.registerExpense(billItemDto)
//
//    verify(saveBillUseCase, times(1)).save(expectedBill, "place")
//  }
//
//  @Test
//  fun openedBillDifferentPlace() {
//    val mockedBill = Bill(
//      1,
//      listOf(BillItem(Product("desc", "", 0.0), 1.0)),,
//      iconUrl = "place"
//    )
//    val expectedBill = Bill(
//      1,
//      listOf(BillItem(Product("desc", "", 0.0), 1.0)),,
//      iconUrl = "place2"
//    )
//
//    val getLastBillUseCase = mock<GetLastBillUseCase> { getLastBill ->
//      on { getLastBill.getLastBill() } doReturn Optional.of(
//        mockedBill
//      )
//    }
//    val billItemValidator =
//      mock<BillItemValidator> { on { it.validate(any()) } doReturn emptyList() }
//    val saveBillUseCase = mock<SaveBillUseCase> {}
//    val registerExpenseUseCase =
//      RegisterExpenseUseCaseImpl(getLastBillUseCase, saveBillUseCase, billItemValidator, get(),)
//
//
//    val calendar = Calendar.getInstance()
//    calendar.time = Date(1)
//    val billItemDto = BillItemDto(ProductDto("desc", "", 0.0), "place2", 1.0, calendar)
//    registerExpenseUseCase.registerExpense(billItemDto)
//
//    verify(saveBillUseCase, times(1)).save(expectedBill, "place2")
//  }
//
//  @Test
//  fun closedBill() {
//    val mockedBill = Bill(
//      1,
//      listOf(BillItem(Product("desc", "", 0.0), 2.0)),, false, iconUrl = "place"
//    )
//    val expectedBill = Bill(
//      1,
//      listOf(BillItem(Product("desc", "", 0.0), 1.0)),,
//      iconUrl = "place"
//    )
//
//    val getLastBillUseCase = mock<GetLastBillUseCase> { getLastBill ->
//      on { getLastBill.getLastBill() } doReturn Optional.of(
//        mockedBill
//      )
//    }
//    val billItemValidator =
//      mock<BillItemValidator> { on { it.validate(any()) } doReturn emptyList() }
//    val saveBillUseCase = mock<SaveBillUseCase> {}
//    val registerExpenseUseCase =
//      RegisterExpenseUseCaseImpl(getLastBillUseCase, saveBillUseCase, billItemValidator, get(),)
//
//
//    val calendar = Calendar.getInstance()
//    val billItemDto = BillItemDto(ProductDto("desc", "", 0.0), "place", 1.0, calendar)
//    calendar.time = Date(1)
//    registerExpenseUseCase.registerExpense(billItemDto)
//
//    verify(saveBillUseCase, times(1)).save(expectedBill, "place")
//  }
//
//  @Test
//  fun testValidatorUsage() {
//    val mockedBill = Bill(
//      1,
//      listOf(BillItem(Product("desc", "", 0.0), 2.0)),, false, iconUrl = "place"
//    )
//
//    val getLastBillUseCase = mock<GetLastBillUseCase> { getLastBill ->
//      on { getLastBill.getLastBill() } doReturn Optional.of(mockedBill)
//    }
//    val billItemValidator =
//      mock<BillItemValidator> { on { it.validate(any()) } doReturn getErrorList() }
//    val saveBillUseCase = mock<SaveBillUseCase> {}
//    val registerExpenseUseCase =
//      RegisterExpenseUseCaseImpl(getLastBillUseCase, saveBillUseCase, billItemValidator, get(),)
//
//
//    val calendar = Calendar.getInstance()
//    val billItemDto = BillItemDto(ProductDto("desc", "", 0.0), "place", 1.0, calendar)
//    calendar.time = Date(1)
//
//    assertEquals(getErrorList(), registerExpenseUseCase.registerExpense(billItemDto))
//
//    verify(saveBillUseCase, times(0)).save(any(), ArgumentMatchers.anyString())
//  }
//
//  private fun getErrorList(): List<RegisterExpenseError> {
//    return listOf(RegisterExpenseError.EMPTY_PLACE, RegisterExpenseError.INVALID_CATEGORY)
//  }
}