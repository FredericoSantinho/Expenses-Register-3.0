package neuro.expenses.register.data.model.place

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import neuro.expenses.register.data.model.RoomPlaceProduct

@Entity(
  primaryKeys = ["placeId", "placeProductId"],
  indices = [Index(value = ["placeProductId"])],
  foreignKeys = [ForeignKey(
    entity = RoomPlace::class,
    parentColumns = arrayOf("placeId"),
    childColumns = arrayOf("placeId"),
    onDelete = ForeignKey.RESTRICT
  ), ForeignKey(
    entity = RoomPlaceProduct::class,
    parentColumns = arrayOf("placeProductId"),
    childColumns = arrayOf("placeProductId"),
    onDelete = ForeignKey.RESTRICT
  )]
)
data class PlacePlaceProductCrossRef(
  val placeId: Long,
  val placeProductId: Long
)
