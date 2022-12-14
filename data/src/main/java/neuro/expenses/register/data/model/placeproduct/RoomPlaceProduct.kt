package neuro.expenses.register.data.model.placeproduct

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import neuro.expenses.register.data.model.category.RoomCategory
import neuro.expenses.register.data.model.product.RoomProduct

@Entity(
  tableName = "place_product_table",
  indices = [Index(
    value = ["productId", "categoryId", "price"],
    unique = true
  ), Index(value = ["categoryId"])],
  foreignKeys = [ForeignKey(
    entity = RoomProduct::class,
    parentColumns = arrayOf("productId"),
    childColumns = arrayOf("productId"),
    onDelete = ForeignKey.NO_ACTION
  ), ForeignKey(
    entity = RoomCategory::class,
    parentColumns = arrayOf("categoryId"),
    childColumns = arrayOf("categoryId"),
    onDelete = ForeignKey.NO_ACTION
  )]
)
data class RoomPlaceProduct(
  @PrimaryKey
  var placeProductId: Long,
  val productId: Long,
  val categoryId: Long,
  val price: Double
)
