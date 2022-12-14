package neuro.expenses.register.data.di

import neuro.expenses.register.data.repository.bill.*
import neuro.expenses.register.data.repository.category.*
import neuro.expenses.register.data.repository.place.*
import neuro.expenses.register.data.repository.product.*
import neuro.expenses.register.domain.repository.bill.*
import neuro.expenses.register.domain.repository.category.*
import neuro.expenses.register.domain.repository.place.*
import neuro.expenses.register.domain.repository.placeproduct.GeneratePlaceProductIdRepository
import neuro.expenses.register.domain.repository.placeproduct.GetPlaceProductRepository
import neuro.expenses.register.domain.repository.placeproduct.SavePlaceProductRepository
import neuro.expenses.register.domain.repository.product.GenerateProductIdRepository
import neuro.expenses.register.domain.repository.product.GetProductRepository
import neuro.expenses.register.domain.repository.product.SaveProductRepository
import org.koin.dsl.module

val repositoryModule = module {
  single<GeneratePlaceProductIdRepository> { GeneratePlaceProductIdRepositoryImpl(get()) }
  single<GenerateCategoryIdRepository> { GenerateCategoryIdRepositoryImpl(get()) }
  single<ObserveCategoriesRepository> { ObserveCategoriesRepositoryImpl(get()) }
  single<GetCategoryRepository> { GetCategoryRepositoryImpl(get()) }
  single<CreateCategoryRepository> { CreateCategoryRepositoryImpl(get()) }
  single<SaveCategoryRepository> { SaveCategoryRepositoryImpl(get()) }
  single<DeleteCategoryRepository> { DeleteCategoryRepositoryImpl(get()) }
  single<UpdateCategoryRepository> { UpdateCategoryRepositoryImpl(get()) }
  single<GenerateBillIdRepository> { GenerateBillIdRepositoryImpl(get()) }
  single<GenerateBillItemIdRepository> { GenerateBillItemIdRepositoryImpl(get()) }
  single<GetBillRepository> { GetBillRepositoryImpl(get()) }
  single<ObserveLastBillRepository> { ObserveLastBillRepositoryImpl(get()) }
  single<ObserveBillsRepository> { ObserveBillsRepositoryImpl(get()) }
  single<GetLastBillRepository> { GetLastBillRepositoryImpl(get()) }
  single<SaveBillRepository> { SaveBillRepositoryImpl(get()) }
  single<SaveProductRepository> { SaveProductRepositoryImpl(get()) }
  single<SavePlaceProductRepository> { SavePlaceProductRepositoryImpl(get()) }
  single<GetProductRepository> { GetProductRepositoryImpl(get()) }
  single<GetPlaceProductRepository> { GetPlaceProductRepositoryImpl(get(), get(), get()) }
  single<GenerateProductIdRepository> { GenerateProductIdRepositoryImpl(get()) }
  single<GeneratePlaceIdRepository> { GeneratePlaceIdRepositoryImpl(get()) }
  single<SavePlaceRepository> { SavePlaceRepositoryImpl(get()) }
  single<ObservePlacesRepository> { ObservePlacesRepositoryImpl(get()) }
  single<GetPlaceRepository> { GetPlaceRepositoryImpl(get()) }
  single<AddPlaceProductRepository> { AddPlaceProductRepositoryImpl(get()) }
  single<RemovePlaceProductRepository> { RemovePlaceProductRepositoryImpl(get()) }
}
