package neuro.expenses.register.entity.di

import neuro.expenses.register.entity.bill.*
import neuro.expenses.register.entity.expense.RegisterExpense
import neuro.expenses.register.entity.expense.RegisterExpenseImpl
import neuro.expenses.register.entity.expense.validator.ExpenseValidator
import neuro.expenses.register.entity.expense.validator.ExpenseValidatorImpl
import neuro.expenses.register.entity.expense.validator.IsValidCategory
import neuro.expenses.register.entity.expense.validator.IsValidCategoryImpl
import neuro.expenses.register.entity.place.GetOrCreatePlace
import neuro.expenses.register.entity.place.GetOrCreatePlaceImpl
import neuro.expenses.register.entity.place.PlaceController
import neuro.expenses.register.entity.place.PlaceControllerImpl
import neuro.expenses.register.entity.placeproduct.GetOrCreatePlaceProduct
import neuro.expenses.register.entity.placeproduct.GetOrCreatePlaceProductImpl
import neuro.expenses.register.entity.product.GetOrCreateProduct
import neuro.expenses.register.entity.product.GetOrCreateProductImpl
import org.koin.dsl.module

val entityModule = module {
  single<RegisterExpense> { RegisterExpenseImpl(get(), get(), get(), get(), get()) }
  single<ExpenseValidator> { ExpenseValidatorImpl(get()) }
  single<IsValidCategory> { IsValidCategoryImpl(get()) }
  single<BillController> {
    BillControllerImpl(get(), get(), get(), get(), get(), get(), get(), get())
  }
  single<SelectBillIconUrl> { SelectBillIconUrlImpl() }
  single<CalculateBillTotal> { CalculateBillTotalImpl() }
  single<GetOrCreateProduct> { GetOrCreateProductImpl(get(), get(), get()) }
  single<GetOrCreatePlaceProduct> {
    GetOrCreatePlaceProductImpl(get(), get(), get(), get(), get())
  }
  single<PlaceController> { PlaceControllerImpl(get(), get(), get()) }
  single<GetOrCreatePlace> { GetOrCreatePlaceImpl(get(), get(), get(), get()) }
}