package neuro.expenses.register.data.mapper.place

import neuro.expenses.register.data.mapper.product.RoomPlaceProductWithProductAndCategoryMapper
import neuro.expenses.register.data.model.place.RoomPlaceWithPlaceProducts
import neuro.expenses.register.domain.dto.PlaceDto

class RoomPlaceWithPlaceProductsMapperImpl(
  private val roomLatLngMapper: RoomLatLngMapper,
  private val roomPlaceProductWithProductAndCategoryMapper: RoomPlaceProductWithProductAndCategoryMapper
) : RoomPlaceWithPlaceProductsMapper {
  override fun map(roomPlaceWithPlaceProducts: RoomPlaceWithPlaceProducts): PlaceDto {
    val roomPlace = roomPlaceWithPlaceProducts.roomPlace

    val name = roomPlace.name
    val latLng = roomLatLngMapper.map(roomPlace.latLng)
    val products = roomPlaceWithPlaceProducts.PlaceProducts.map {
      roomPlaceProductWithProductAndCategoryMapper.map(it)
    }

    return PlaceDto(name, products, latLng)
  }

  override fun map(roomPlaceWithPlaceProductslist: List<RoomPlaceWithPlaceProducts>): List<PlaceDto> {
    return roomPlaceWithPlaceProductslist.map { map(it) }
  }
}