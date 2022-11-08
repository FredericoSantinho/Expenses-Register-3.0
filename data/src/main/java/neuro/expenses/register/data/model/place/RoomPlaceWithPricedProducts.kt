package neuro.expenses.register.data.model.place

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import neuro.expenses.register.data.model.RoomPricedProduct
import neuro.expenses.register.data.model.product.RoomPricedProductWithProductAndCategory

data class RoomPlaceWithPricedProducts(
  @Embedded val roomPlace: RoomPlace,
  @Relation(
    entity = RoomPricedProduct::class,
    parentColumn = "name",
    entityColumn = "pricedProductId",
    associateBy = Junction(PlacePricedProductCrossRef::class)
  )
  val pricedProducts: List<RoomPricedProductWithProductAndCategory>,
)