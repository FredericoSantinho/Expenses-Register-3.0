package neuro.expenses.register.data.model.product

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import neuro.expenses.register.data.model.RoomCategory
import neuro.expenses.register.data.model.RoomPlaceProduct

@Entity(
  primaryKeys = ["placeProductId", "categoryId"],
  indices = [Index(value = ["categoryId"])],
  foreignKeys = [ForeignKey(
    entity = RoomPlaceProduct::class,
    parentColumns = arrayOf("placeProductId"),
    childColumns = arrayOf("placeProductId"),
    onDelete = ForeignKey.NO_ACTION
  ), ForeignKey(
    entity = RoomCategory::class,
    parentColumns = arrayOf("categoryId"),
    childColumns = arrayOf("categoryId"),
    onDelete = ForeignKey.NO_ACTION
  )]
)
data class PlaceProductCategoryCrossRef(
  val placeProductId: Long,
  val categoryId: Long
)
