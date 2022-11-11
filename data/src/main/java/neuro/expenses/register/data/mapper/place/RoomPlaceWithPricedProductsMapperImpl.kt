package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.mapper.product.RoomPricedProductWithProductAndCategoryMapper
import neuro.expenses.register.data.model.place.RoomPlaceWithPricedProducts
import neuro.expenses.register.domain.dto.PlaceDto

class RoomPlaceWithPricedProductsMapperImpl(
  private val roomLatLngMapper: RoomLatLngMapper,
  private val roomPricedProductWithProductAndCategoryMapper: RoomPricedProductWithProductAndCategoryMapper
) : RoomPlaceWithPricedProductsMapper {
  override fun map(roomPlaceWithPricedProducts: RoomPlaceWithPricedProducts): PlaceDto {
    val roomPlace = roomPlaceWithPricedProducts.roomPlace

    val name = roomPlace.name
    val latLng = roomLatLngMapper.map(roomPlace.latLng)
    val products = roomPlaceWithPricedProducts.pricedProducts.map {
      roomPricedProductWithProductAndCategoryMapper.map(it)
    }

    return PlaceDto(name, products, latLng)
  }
}