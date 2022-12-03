package neuro.expenses.register.data.model.placeproduct

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import neuro.expenses.register.data.model.product.RoomProduct

@Entity(
  primaryKeys = ["placeProductId", "productId"],
  indices = [Index(value = ["productId"])],
  foreignKeys = [ForeignKey(
    entity = RoomPlaceProduct::class,
    parentColumns = arrayOf("placeProductId"),
    childColumns = arrayOf("placeProductId"),
    onDelete = ForeignKey.NO_ACTION
  ), ForeignKey(
    entity = RoomProduct::class,
    parentColumns = arrayOf("productId"),
    childColumns = arrayOf("productId"),
    onDelete = ForeignKey.NO_ACTION
  )]
)
data class PlaceProductProductCrossRef(
  val placeProductId: Long,
  val productId: Long
)
