package neuro.expenses.register.entity.di


import neuro.expenses.register.entity.controller.bill.*
import neuro.expenses.register.entity.controller.expense.RegisterExpense
import neuro.expenses.register.entity.controller.expense.RegisterExpenseImpl
import neuro.expenses.register.entity.controller.expense.validator.ExpenseValidator
import neuro.expenses.register.entity.controller.expense.validator.ExpenseValidatorImpl
import neuro.expenses.register.entity.controller.expense.validator.IsValidCategory
import neuro.expenses.register.entity.controller.expense.validator.IsValidCategoryImpl
import neuro.expenses.register.entity.controller.place.GetOrCreatePlace
import neuro.expenses.register.entity.controller.place.GetOrCreatePlaceImpl
import neuro.expenses.register.entity.controller.place.PlaceController
import neuro.expenses.register.entity.controller.place.PlaceControllerImpl
import neuro.expenses.register.entity.controller.product.GetOrCreateProduct
import neuro.expenses.register.entity.controller.product.GetOrCreateProductImpl
import org.koin.dsl.module

val controllerModule = module {
  single<RegisterExpense> { RegisterExpenseImpl(get(), get(), get(), get(), get(), get()) }
  single<ExpenseValidator> { ExpenseValidatorImpl(get()) }
  single<IsValidCategory> { IsValidCategoryImpl(get()) }
  single<BillController> {
    BillController(
      get(),
      get(),
      get(),
      get(),
      get(),
      get()
    )
  }
  single<GetBillIconUrl> { GetBillIconUrlImpl() }
  single<CalculateBillTotal> { CalculateBillTotalImpl() }
  single<GetOrCreateProduct> { GetOrCreateProductImpl(get(), get(), get(), get(), get()) }
  single<PlaceController> { PlaceControllerImpl() }
  single<GetOrCreatePlace> { GetOrCreatePlaceImpl(get(), get(), get()) }
}