package neuro.expenses.register.data.di

import neuro.expenses.register.data.mapper.RoomCategoriesMapper
import neuro.expenses.register.data.mapper.RoomCategoriesMapperImpl
import neuro.expenses.register.data.mapper.bill.*
import neuro.expenses.register.data.mapper.place.LatLngMapper
import neuro.expenses.register.data.mapper.place.LatLngMapperImpl
import neuro.expenses.register.data.mapper.place.RoomPlaceWithPricedProductsMapper
import neuro.expenses.register.data.mapper.place.RoomPlaceWithPricedProductsMapperImpl
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
  single<RoomPlaceWithPricedProductsMapper> { RoomPlaceWithPricedProductsMapperImpl(get(), get()) }
  single<LatLngMapper> { LatLngMapperImpl() }
}
