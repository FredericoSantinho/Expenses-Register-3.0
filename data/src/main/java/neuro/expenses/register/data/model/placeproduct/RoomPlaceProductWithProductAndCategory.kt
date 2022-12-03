package neuro.expenses.register.data.model.placeproduct

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import neuro.expenses.register.data.model.category.RoomCategory
import neuro.expenses.register.data.model.product.RoomProduct

data class RoomPlaceProductWithProductAndCategory(
  @Embedded val roomPlaceProduct: RoomPlaceProduct,
  @Relation(
    parentColumn = "placeProductId",
    entityColumn = "productId",
    associateBy = Junction(PlaceProductProductCrossRef::class)
  )
  val roomProduct: RoomProduct,
  @Relation(
    parentColumn = "placeProductId",
    entityColumn = "categoryId",
    associateBy = Junction(PlaceProductCategoryCrossRef::class)
  )
  val category: RoomCategory
)