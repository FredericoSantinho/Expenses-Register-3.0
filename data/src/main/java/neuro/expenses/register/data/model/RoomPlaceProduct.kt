package neuro.expenses.register.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

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
    onDelete = ForeignKey.RESTRICT
  ), ForeignKey(
    entity = RoomCategory::class,
    parentColumns = arrayOf("categoryId"),
    childColumns = arrayOf("categoryId"),
    onDelete = ForeignKey.RESTRICT
  )]
)
data class RoomPlaceProduct(
  @PrimaryKey
  var placeProductId: Long = 0,
  val productId: Long,
  val categoryId: Long,
  val price: Double
)
