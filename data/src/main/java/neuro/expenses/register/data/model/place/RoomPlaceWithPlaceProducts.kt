package neuro.expenses.register.data.model.place

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import neuro.expenses.register.data.model.RoomPlaceProduct
import neuro.expenses.register.data.model.product.RoomPlaceProductWithProductAndCategory

data class RoomPlaceWithPlaceProducts(
  @Embedded val roomPlace: RoomPlace,
  @Relation(
    entity = RoomPlaceProduct::class,
    parentColumn = "placeId",
    entityColumn = "placeProductId",
    associateBy = Junction(PlacePlaceProductCrossRef::class)
  )
  val PlaceProducts: List<RoomPlaceProductWithProductAndCategory>,
)