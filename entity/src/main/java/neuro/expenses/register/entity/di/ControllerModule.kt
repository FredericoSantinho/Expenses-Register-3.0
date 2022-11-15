package neuro.expenses.register.entity.di

import neuro.expenses.register.entity.controller.BillController
import neuro.expenses.register.entity.controller.PlaceController
import neuro.expenses.register.entity.controller.PlaceControllerImpl
import org.koin.dsl.module

val controllerModule = module {
  single<BillController> { BillController(get(), get(), get()) }
  single<PlaceController> { PlaceControllerImpl() }
}