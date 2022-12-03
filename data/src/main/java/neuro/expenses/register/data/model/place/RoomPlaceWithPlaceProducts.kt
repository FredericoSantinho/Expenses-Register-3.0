package neuro.expenses.register.data.model.place

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import neuro.expenses.register.data.model.placeproduct.RoomPlaceProduct
import neuro.expenses.register.data.model.placeproduct.RoomPlaceProductWithProductAndCategory

data class RoomPlaceWithPlaceProducts(
  @Embedded val roomPlace: RoomPlace,
  @Relation(
    entity = RoomPlaceProduct::class,
    parentColumn = "placeId",
    entityColumn = "placeProductId",
    associateBy = Junction(PlacePlaceProductCrossRef::class)
  )
  val placeProducts: List<RoomPlaceProductWithProductAndCategory>,
)