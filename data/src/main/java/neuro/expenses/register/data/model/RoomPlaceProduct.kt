package neuro.expenses.register.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import neuro.expenses.register.data.model.place.RoomPlace

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
    onDelete = ForeignKey.CASCADE
  ), ForeignKey(
    entity = RoomCategory::class,
    parentColumns = arrayOf("categoryId"),
    childColumns = arrayOf("categoryId"),
    onDelete = ForeignKey.CASCADE
  ), ForeignKey(
    entity = RoomPlace::class,
    parentColumns = arrayOf("placeId"),
    childColumns = arrayOf("placeId"),
    onDelete = ForeignKey.CASCADE
  )]
)
data class RoomPlaceProduct(
  @PrimaryKey
  var placeProductId: Long = 0,
  val productId: Long,
  val categoryId: Long,
  val price: Double,
  val defaultAmount: Double,
  val placeId: Long
)
