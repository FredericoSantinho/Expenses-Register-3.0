package neuro.expenses.register.data.di

import neuro.expenses.register.data.mapper.RoomCategoriesMapper
import neuro.expenses.register.data.mapper.RoomCategoriesMapperImpl
import neuro.expenses.register.data.mapper.bill.*
import neuro.expenses.register.data.mapper.place.*
import neuro.expenses.register.data.mapper.product.RoomPricedProductWithProductAndCategoryMapper
import neuro.expenses.register.data.mapper.product.RoomPricedProductWithProductAndCategoryMapperImpl
import org.koin.dsl.module

val databaseMapperModule = module {
  single<RoomCategoriesMapper> { RoomCategoriesMapperImpl() }
  single<RoomBillWithBillItemsMapper> { RoomBillWithBillItemsMapperImpl(get()) }
  single<RoomBillItemWithPricedProductMapper> { RoomBillItemWithPricedProductMapperImpl(get()) }
  single<RoomPricedProductWithProductAndCategoryMapper> { RoomPricedProductWithProductAndCategoryMapperImpl() }
  single<RoomBillMapper> { RoomBillMapperImpl() }
  single<RoomBillItemMapper> { RoomBillItemMapperImpl() }
  single<RoomPlaceMapper> { RoomPlaceMapperImpl(get()) }
  single<RoomPlaceWithPricedProductsMapper> { RoomPlaceWithPricedProductsMapperImpl(get(), get()) }
  single<RoomLatLngMapper> { RoomLatLngMapperImpl() }
}
