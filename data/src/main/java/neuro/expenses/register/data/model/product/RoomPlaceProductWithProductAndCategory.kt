package neuro.expenses.register.data.model.product

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import neuro.expenses.register.data.model.RoomCategory
import neuro.expenses.register.data.model.RoomPlaceProduct
import neuro.expenses.register.data.model.RoomProduct

data class RoomPlaceProductWithProductAndCategory(
  @Embedded val roomPlaceProduct: RoomPlaceProduct,
  @Relation(
    parentColumn = "placeProductId",
    entityColumn = "productId",
    associateBy = Junction(PlaceProductProductCrossRef::class)
  )
  val roomProduct: List<RoomProduct>,
  @Relation(
    parentColumn = "placeProductId",
    entityColumn = "categoryId",
    associateBy = Junction(PlaceProductCategoryCrossRef::class)
  )
  val category: List<RoomCategory>
)