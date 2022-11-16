package neuro.expenses.register.data.di

import neuro.expenses.register.data.mapper.bill.*
import neuro.expenses.register.data.mapper.category.RoomCategoryMapper
import neuro.expenses.register.data.mapper.category.RoomCategoryMapperImpl
import neuro.expenses.register.data.mapper.place.*
import neuro.expenses.register.data.mapper.product.RoomPlaceProductWithProductAndCategoryMapper
import neuro.expenses.register.data.mapper.product.RoomPlaceProductWithProductAndCategoryMapperImpl
import org.koin.dsl.module

val databaseMapperModule = module {
  single<RoomCategoryMapper> { RoomCategoryMapperImpl() }
  single<RoomBillWithBillItemsMapper> { RoomBillWithBillItemsMapperImpl(get(), get()) }
  single<RoomBillItemWithPlaceProductMapper> { RoomBillItemWithPlaceProductMapperImpl(get()) }
  single<RoomPlaceProductWithProductAndCategoryMapper> {
    RoomPlaceProductWithProductAndCategoryMapperImpl(
      get()
    )
  }
  single<RoomBillMapper> { RoomBillMapperImpl() }
  single<RoomBillItemMapper> { RoomBillItemMapperImpl() }
  single<RoomPlaceMapper> { RoomPlaceMapperImpl(get()) }
  single<RoomPlaceWithPlaceProductsMapper> { RoomPlaceWithPlaceProductsMapperImpl(get(), get()) }
  single<RoomLatLngMapper> { RoomLatLngMapperImpl() }
}
