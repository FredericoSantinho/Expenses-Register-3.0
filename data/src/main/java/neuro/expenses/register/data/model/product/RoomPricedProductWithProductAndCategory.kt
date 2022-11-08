package neuro.expenses.register.data.model.product

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import neuro.expenses.register.data.model.RoomCategory
import neuro.expenses.register.data.model.RoomPricedProduct
import neuro.expenses.register.data.model.RoomProduct

data class RoomPricedProductWithProductAndCategory(
  @Embedded val roomPricedProduct: RoomPricedProduct,
  @Relation(
    parentColumn = "pricedProductId",
    entityColumn = "productId",
    associateBy = Junction(PricedProductProductCrossRef::class)
  )
  val roomProduct: List<RoomProduct>,
  @Relation(
    parentColumn = "pricedProductId",
    entityColumn = "name",
    associateBy = Junction(PricedProductCategoryCrossRef::class)
  )
  val category: List<RoomCategory>
)